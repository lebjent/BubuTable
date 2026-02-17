package com.java.point.app.member.controller;

import com.java.point.app.member.dto.UserJoinRequest;
import com.java.point.app.member.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/members")
public class UserController {

    private final UserService userService;

    // 회원가입 페이지 이동
    @GetMapping("/join")
    public String joinForm() {
        return "members/joinForm";
    }

    // 회원가입 처리
    @PostMapping("/join")
    public String join(UserJoinRequest request) {
        userService.join(request);
        return "redirect:/"; // 가입 성공 시 메인 페이지로 이동
    }
}
