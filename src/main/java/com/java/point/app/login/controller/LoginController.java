package com.java.point.app.login.controller;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class LoginController {
    // 1. 로그인 페이지로 이동
    @GetMapping("/login")
    public String loginForm() {
        return "login/loginForm"; // templates/members/loginForm.html 호출
    }

    // 3. 로그아웃 처리
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate(); // 세션 무효화
        return "redirect:/";
    }
}
