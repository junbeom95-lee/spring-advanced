package org.example.expert.common.util.jwt;

import org.example.expert.domain.user.enums.UserRole;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.test.util.ReflectionTestUtils;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class JwtUtilTest {

    private JwtUtil jwtUtil;

    @BeforeEach
    void setUp() {
        jwtUtil = new JwtUtil();

        String secretKey = "QWEASDzxcASDzxcrtyfghvbnuiojklmqweasdzxcasdHFGHGFHGF";
        ReflectionTestUtils.setField(jwtUtil, "secretKey", secretKey);
        jwtUtil.init();
    }


    @Test
    @DisplayName("JWT 토큰 생성 TEST - 성공 케이스")
    void validateToken_success() {

        //given
        Long userId = 1L;
        String email = "lee@seo.jun";
        UserRole role = UserRole.ADMIN;

        //when
        String token = jwtUtil.createToken(userId, email, role);

        //then
        assertThat(token).startsWith("Bearer ");

    }

}