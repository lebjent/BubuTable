package com.java.point.app.test;

import java.util.Random;

public class ScreeningService {
    private final Random random = new Random();

    // 호출 시 1초가 소요되는 가상의 심사 로직
    public String checkRisk(String taskName) {
        try {
            System.out.println("[시작] " + taskName + " 심사 중... (Thread: " + Thread.currentThread() + ")");
            Thread.sleep(1000); // 1초 대기
            return "심사 완료 (결과: PASS)";
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            return "오류 발생";
        }
    }
}
