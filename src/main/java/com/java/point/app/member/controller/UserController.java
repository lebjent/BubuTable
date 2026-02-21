package com.java.point.app.member.controller;

import com.java.point.app.common.dto.ApiResponse;
import com.java.point.app.member.dto.UserJoinRequest;
import com.java.point.app.member.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;

    // 회원가입 페이지 이동
    @GetMapping("/join")
    public String joinForm() {
        return "members/joinForm";
    }

    // 회원가입 처리
    @PostMapping("/join")
    public ResponseEntity<?> join(@RequestBody UserJoinRequest request, org.springframework.ui.Model model) {
        try {
            Long userId = userService.join(request);

            // 성공 응답 (200 OK)
            return ResponseEntity.ok(
                    ApiResponse.success("부부 테이블의 요리사가 되신 것을 환영해요! 🍳", userId)
            );

        } catch (IllegalStateException e) {
            // 실패 응답 (400 Bad Request)
            // e.getMessage()는 "이미 존재하는 이메일입니다" 등이 넘어옵니다.
            return ResponseEntity.badRequest().body(
                    ApiResponse.error(e.getMessage())
            );
        } catch (Exception e) {
            // 예상치 못한 서버 에러 (500 Internal Server Error)
            return ResponseEntity.internalServerError().body(
                    ApiResponse.error("서버 오류가 발생했습니다. 잠시 후 다시 시도해주세요.")
            );
        }
    }

    @GetMapping("/check-nickname")
    public ResponseEntity<ApiResponse<Boolean>> checkNickname(@RequestParam("nickname") String nickname) {
        boolean available = !userService.checkNickname(nickname);

        if (available) {
            return ResponseEntity.ok(ApiResponse.success("사용 가능한 닉네임입니다.", true));
        } else {
            // 중복된 경우에도 200 OK를 주되, 내부 success를 false로 주거나
            // 아예 규격화된 성공/실패로 응답합니다.
            return ResponseEntity.ok(ApiResponse.success("이미 사용 중인 닉네임입니다.", false));
        }
    }

    @GetMapping("/check-email")
    public ResponseEntity<ApiResponse<Boolean>> checkEmail(@RequestParam("email") String email) {
        // 이미 가입된 이메일이 없어야(false) 사용 가능(true)
        boolean available = !userService.checkEmail(email);

        String message = available ? "사용 가능한 이메일입니다." : "이미 가입된 이메일 주소입니다! 📬";
        return ResponseEntity.ok(ApiResponse.success(message, available));
    }
}
