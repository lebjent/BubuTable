package com.java.point.app.common.util;

import java.util.*;
import java.util.concurrent.*;
import java.util.function.Supplier;

public class ParallelExecutor implements AutoCloseable {

    private final ExecutorService executor = Executors.newVirtualThreadPerTaskExecutor();
    private final Semaphore semaphore;

    public ParallelExecutor(int maxConcurrentTasks) {
        this.semaphore = new Semaphore(maxConcurrentTasks);
    }

    /**
     * Key와 로직을 받아 병렬 실행 후 결과를 리턴함
     */
    public <K, V> List<Map.Entry<K, V>> executeTasks(Map<K, Supplier<V>> tasks) {
        List<CompletableFuture<Map.Entry<K, V>>> futures = new ArrayList<>();

        for (var entry : tasks.entrySet()) {
            CompletableFuture<Map.Entry<K, V>> future = CompletableFuture.supplyAsync(() -> {
                try {
                    // 세마포어로 동시성 제어
                    semaphore.acquire();
                    V result = entry.getValue().get();
                    return Map.entry(entry.getKey(), result);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    throw new RuntimeException(e);
                } finally {
                    semaphore.release();
                }
            }, executor);

            futures.add(future);
        }

        // 모든 작업이 끝날 때까지 기다려 리스트로 반환
        return futures.stream()
                .map(CompletableFuture::join)
                .toList();
    }

    @Override
    public void close() {
        executor.close();
    }
}