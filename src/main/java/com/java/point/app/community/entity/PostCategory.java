package com.java.point.app.community.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum PostCategory {
    DAILY("오늘하루"),             // 일상 공유
    COUNSELING("고민상담"),         // 신혼부부 고민
    LOCALRESTRAUNT("우리동네맛집"),  // 직접 가서 먹는 맛집
    DELIVERY("배달맛집추천"),       // 🛵 배달 맛집 (추가)
    MARKET("장보기팁"),            // 알뜰 장보기 정보
    QNA("질문답변");               // 요리나 가전 질문

    private final String description;
}