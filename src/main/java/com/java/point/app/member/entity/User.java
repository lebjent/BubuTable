package com.java.point.app.member.entity;

import com.java.point.app.common.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "TB_MB_USER", schema = "DPRO")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED) // 무분별한 생성을 막음
public class User extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "USER_ID")
    private Long id;

    @Column(nullable = false, unique = true, length = 100)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false, length = 50)
    private String nickname;

    @Column(name = "USER_ROLE", length = 20)
    private String userRole = "USER";

    @Builder // 빌더 패턴으로 안전하게 객체 생성
    public User(String email, String password, String nickname) {
        this.email = email;
        this.password = password;
        this.nickname = nickname;
    }
}
