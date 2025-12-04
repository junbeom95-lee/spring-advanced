package org.example.expert.common.exception;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.example.expert.common.enums.ExceptionCode;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.net.URI;

@RestControllerAdvice
@RequiredArgsConstructor
public class GlobalExceptionHandler {

    private final HttpServletRequest request;

    @ExceptionHandler(ServerException.class)
    public ProblemDetail handleServerException(ServerException ex) {

        ExceptionCode exceptionCode = ex.getExceptionCode();

        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(
                exceptionCode.getStatus(),
                exceptionCode.getMessage()
        );

        problemDetail.setType(URI.create(request.getRequestURI()));
        problemDetail.setTitle(exceptionCode.name());

        return problemDetail;
    }

}

