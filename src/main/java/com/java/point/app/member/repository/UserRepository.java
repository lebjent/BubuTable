package com.java.point.app.member.repository;

import com.java.point.app.member.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {
    // 이메일로 중복 가입 여부를 확인하기 위해 추가
    Optional<User> findByEmail(String email);

    // 닉네임 중복 확인을 위해 추가
    boolean existsByNickname(String nickname);

    boolean existsByEmail(String email);
}
