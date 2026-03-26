package com.java.point.app.test;

import com.java.point.app.common.util.ParallelExecutor;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

public class ParallelTestApp {
    public static void main(String[] args) {
        ScreeningService service = new ScreeningService();

        // 동시 실행 수를 5개로 제한하는 실행기 생성
        try (ParallelExecutor executor = new ParallelExecutor(5)) {

            // 1. 테스트 데이터 준비 (4개의 심사 항목)
            Map<String, Supplier<String>> taskMap = new HashMap<>();
            taskMap.put("질병이력조회", () -> service.checkRisk("질병이력"));
            taskMap.put("직업위험도조회", () -> service.checkRisk("직업위험"));
            taskMap.put("기가입현황조회", () -> service.checkRisk("기가입현황"));
            taskMap.put("신용정보조회", () -> service.checkRisk("신용정보"));

            System.out.println(">>> 병렬 처리 시작");
            long startTime = System.currentTimeMillis();

            // 2. 병렬 실행 및 결과 수집
            List<Map.Entry<String, String>> results = executor.executeTasks(taskMap);

            long endTime = System.currentTimeMillis();
            System.out.println(">>> 모든 작업 완료!");

            // 3. 결과 출력 및 검증
            System.out.println("--------------------------------------");
            results.forEach(res ->
                    System.out.println("Key: " + res.getKey() + " | Value: " + res.getValue())
            );
            System.out.println("--------------------------------------");
            System.out.println("총 소요 시간: " + (endTime - startTime) + "ms");
            System.out.println("(순차 실행 시 4000ms가 걸려야 하지만, 약 1000ms 내외로 완료되어야 함)");
        }
    }
}
