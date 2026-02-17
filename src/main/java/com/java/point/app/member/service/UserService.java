package com.java.point.app.member.service;

import com.java.point.app.member.dto.UserJoinRequest;
import com.java.point.app.member.entity.User;
import com.java.point.app.member.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class UserService {

    private final UserRepository userRepository;

    public Long join(UserJoinRequest request) {
        // 1. 중복 검사
        userRepository.findByEmail(request.email())
                .ifPresent(u -> { throw new IllegalStateException("이미 존재하는 이메일입니다."); });

        // 2. 엔티티 변환 및 저장
        User user = User.builder()
                .email(request.email())
                .password(request.password()) // 원래는 암호화 필수!
                .nickname(request.nickname())
                .build();

        return userRepository.save(user).getId();
    }

}
