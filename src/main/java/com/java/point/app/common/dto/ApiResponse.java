package com.java.point.app.common.dto;

import lombok.Builder;

@Builder
public record ApiResponse<T>(
        boolean success,    // 성공 여부
        String message,     // 사용자에게 보여줄 메시지
        T data              // 실제 결과 데이터 (필요 없으면 null)
) {
    // 성공 시 편하게 쓰기 위한 정적 팩토리 메서드
    public static <T> ApiResponse<T> success(String message, T data) {
        return new ApiResponse<>(true, message, data);
    }

    public static <T> ApiResponse<T> success(String message) {
        return new ApiResponse<>(true, message, null);
    }

    // 실패 시 편하게 쓰기 위한 정적 팩토리 메서드
    public static <T> ApiResponse<T> error(String message) {
        return new ApiResponse<>(false, message, null);
    }
}
