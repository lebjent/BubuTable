package com.java.point.app.community.dto;

import java.time.LocalDateTime;

public record PostListResponse(
        Long id,
        String category,
        String title,
        String authorName,
        int viewCount,
        LocalDateTime createdAt
) {}
