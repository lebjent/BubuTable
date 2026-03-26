package com.java.point.app.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/", "/login", "/join", "/css/**", "/js/**", "/api/user/**").permitAll()
                        .requestMatchers("/community/write").authenticated() // 글쓰기는 로그인 필수
                        .requestMatchers("/community", "/community/{id}").permitAll() // 목록과 상세글은 누구나 가능
                        .anyRequest().authenticated()
                )
                .formLogin(form -> form
                        .loginPage("/login")
                        .loginProcessingUrl("/api/user/login") // JS에서 호출하는 URL
                        // 로그인 성공 시 처리
                        .successHandler((request, response, authentication) -> {
                            response.setContentType("application/json;charset=UTF-8");
                            response.getWriter().write("{\"success\": true, \"message\": \"로그인에 성공했습니다!\"}");
                        })
                        // 로그인 실패 시 처리
                        .failureHandler((request, response, exception) -> {
                            response.setStatus(401); // Unauthorized
                            response.setContentType("application/json;charset=UTF-8");
                            response.getWriter().write("{\"success\": false, \"message\": \"이메일 또는 비밀번호를 확인해주세요.\"}");
                        })
                        .permitAll()
                )
                .logout(logout -> logout.logoutSuccessUrl("/"));

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(); // 비밀번호 암호화 도구 등록
    }
}
