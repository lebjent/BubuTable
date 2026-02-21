package com.java.point.app.member.dto;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collections;

/**
 * 시큐리티가 로그인 완료 후 세션에 저장할 사용자 정보
 */
public class MemberContext extends User{

    private final String nickname;

    public MemberContext(com.java.point.app.member.entity.User user) {
        // ID(이메일), PW, 권한을 부모 클래스에 전달
        super(user.getEmail(), user.getPassword(),
                Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER")));
        this.nickname = user.getNickname();
    }

    public String getNickname() {
        return nickname;
    }
}
