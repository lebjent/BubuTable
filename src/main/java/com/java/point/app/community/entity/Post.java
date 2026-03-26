package com.java.point.app.community.entity;

import com.java.point.app.common.entity.BaseEntity;
import com.java.point.app.member.entity.User;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Getter
@Table(name = "TB_CM_POST", schema = "dpro")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EntityListeners(AuditingEntityListener.class)
public class Post extends BaseEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private PostCategory category; // 고민상담, 장보기팁, 자유 등

    private String subCategory;    // 소분류 (예: "강남구", "정자동", "치킨" 등)

    @Column(nullable = false)
    private String title;

    @Lob
    @Column(nullable = false)
    private String content;

    private int viewCount = 0;

    private int likeCount = 0;    // 추가: 좋아요 수

    private String thumbnail;     // 추가: 리스트용 대표 이미지 경로

    @Column(nullable = false)
    private boolean deleted = false; // 추가: 삭제 여부 (Soft Delete)

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User author; // Spring Security 유저와 연동

    @Builder
    public Post(PostCategory category, String subCategory, String title, String content, String thumbnail, User author) {
        this.category = category;
        this.subCategory = subCategory;
        this.title = title;
        this.content = content;
        this.author = author;
        this.thumbnail = thumbnail;
    }

    // 비즈니스 로직 추가
    public void delete() { this.deleted = true; }
    public void addLike() { this.likeCount++; }
}
