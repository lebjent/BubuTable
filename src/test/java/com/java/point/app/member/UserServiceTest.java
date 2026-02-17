package com.java.point.app.member;

import com.java.point.app.member.dto.UserJoinRequest;
import com.java.point.app.member.service.UserService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import static org.junit.jupiter.api.Assertions.assertNotNull; // 핵심!

@SpringBootTest
@Transactional
public class UserServiceTest {
    @Autowired
    UserService userService;

    @Test
    @DisplayName("회원가입이 성공해야 한다")
    void joinTest() {
        // given
        UserJoinRequest dto = new UserJoinRequest("test@test.com", "1234", "부부테스터");

        // when
        Long savedId = userService.join(dto);

        // then
        assertNotNull(savedId, "저장된 유저의 ID는 null이 아니어야 합니다.");

    }
}
