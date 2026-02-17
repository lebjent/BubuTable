package com.java.point.app.member.dto;

public record UserJoinRequest(
        String email,
        String password,
        String nickname
) {}
