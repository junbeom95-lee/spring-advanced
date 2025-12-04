package org.example.expert.config;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.LocalDateTime;

@Slf4j
@Aspect
@Component
@RequiredArgsConstructor
public class LogAop {

    private final ObjectMapper objectMapper;
    private final HttpServletRequest request;

    //어떤 것을 : 어드민 사용자만 접근하는 메서드
    //언제 : 실행 전 ? 접근할 때마다
    //어떻게 : 로깅

    @Around("execution(* org.example.expert.domain.*.controller.*AdminController.*(..))")
    public Object executionLog(ProceedingJoinPoint joinPoint) throws IOException {

        //요청한 사용자의 ID <- Filter에서 SetAttribute 값
        Long userId = (Long) request.getAttribute("userId");

        //API 요청 시각
        LocalDateTime now = LocalDateTime.now();

        //API 요청 URL
        String uri = request.getRequestURI();

        log.info("[AOP] :: userId : {}, uri : {}, time : {}", userId, uri,  now);

        //요청 본문
        getRequestBodyLogging(joinPoint);

        Object result = null;

        try {

            result = joinPoint.proceed();    //메서드 실행

        } catch (Throwable e) {

            log.error("[AOP] :: cause : {}, message : {}", e.getCause(), e.getMessage());

        } finally {

            log.info("[AOP] :: result : {}", getResponseBodyString(result));

            return result;
        }
    }

    private void getRequestBodyLogging(ProceedingJoinPoint joinPoint) throws JsonProcessingException {

        for (Object arg : joinPoint.getArgs()) {
            log.info("[AOP] Request Args : {}", objectMapper.writeValueAsString(arg));
        }
    }

    private String getResponseBodyString(Object result) throws JsonProcessingException {
        return objectMapper.writeValueAsString(result);
    }

}
