package com.java.point.app.community.dto;

import com.java.point.app.community.entity.Post;
import com.java.point.app.community.entity.PostCategory;
import com.java.point.app.member.entity.User;

public record PostSaveRequest(
        String title,
        String content,
        PostCategory category,
        String subCategory,
        String thumbnail
) {
    // record 내부에서도 toEntity 메서드를 만들 수 있습니다.
    public Post toEntity(User author) {
        return Post.builder()
                .title(title)
                .content(content)
                .category(category)
                .subCategory(subCategory)
                .thumbnail(thumbnail)
                .author(author)
                .build();
    }
}
