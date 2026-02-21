package com.java.point.app.member.service;

import com.java.point.app.member.dto.MemberContext;
import com.java.point.app.member.entity.User;
import com.java.point.app.member.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        // 1. 이메일로 유저를 찾음
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("가입되지 않은 이메일입니다: " + email));

        // 2. 시큐리티용 세션 객체(MemberContext)로 변환해서 반환
        // 시큐리티는 여기서 반환된 PW와 사용자가 입력한 PW를 자동으로 비교해줍니다.
        return new MemberContext(user);
    }
}
