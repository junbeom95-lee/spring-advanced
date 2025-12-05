package org.example.expert.domain.auth.service;

import org.example.expert.common.enums.ExceptionCode;
import org.example.expert.domain.auth.dto.request.SigninRequest;
import org.example.expert.domain.auth.exception.AuthException;
import org.example.expert.domain.user.repository.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class AuthServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private AuthService authService;

    @Test
    @DisplayName("로그인 테스트 - 실패 케이스 - 로그인 시 아이디가 없을 때")
    void signin_fail() {

        //given
        String password = "TestTestTest1";
        String notEmail = "lee@jun.beum";
        SigninRequest signinRequest = new SigninRequest(notEmail, password);
        given(userRepository.findByEmail(anyString())).willReturn(Optional.empty());

        //when
        AuthException exception = assertThrows(AuthException.class, () ->
                authService.signin(signinRequest));

        //then
        assertEquals(ExceptionCode.NOT_FOUND_SIGNUP_USER, exception.getExceptionCode());
        assertEquals(HttpStatus.BAD_REQUEST, exception.getExceptionCode().getStatus());
        assertEquals("가입되지 않은 유저입니다.", exception.getExceptionCode().getMessage());
    }
}