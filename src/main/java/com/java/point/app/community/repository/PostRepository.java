package com.java.point.app.community.repository;

import com.java.point.app.community.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {

    // 1. 쿼리 메소드 방식 (이름으로 생성)
    List<Post> findAllByOrderByIdDesc();

}
