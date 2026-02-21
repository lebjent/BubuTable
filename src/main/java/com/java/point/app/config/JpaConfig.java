package com.java.point.app.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;

/**
 * JPA Auditing 기능을 활성화하는 설정 클래스
 * BaseEntity의 @CreatedDate, @LastModifiedDate 작동을 담당합니다.
 */
@Configuration
@EnableJpaAuditing
public class JpaConfig {
    // 예시: 현재 로그인한 유저의 ID를 감시자(Auditor)로 등록
    @Bean
    public AuditorAware<String> auditorProvider() {
        return () -> {
            var authentication = SecurityContextHolder.getContext().getAuthentication();

            // 1. 인증 정보가 아예 없거나
            // 2. 인증은 되었으나 '익명 사용자'인 경우 (로그인 전 가입 시점 등)
            if (authentication == null ||
                    !authentication.isAuthenticated() ||
                    "anonymousUser".equals(authentication.getPrincipal())) {

                return Optional.of("SYSTEM"); // 로그인 전에는 SYSTEM으로 기록
            }

            // 로그인 된 상태라면 해당 유저의 이름(Email 등)을 기록
            return Optional.ofNullable(authentication.getName());
        };
    }
}
