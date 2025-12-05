package org.example.expert.common.exception;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.example.expert.common.enums.ExceptionCode;
import org.springframework.http.ProblemDetail;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.net.URI;
import java.util.HashMap;
import java.util.List;

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

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ProblemDetail methodArgumentNotValidException(MethodArgumentNotValidException e) {

        HashMap<String, Object> message = getErrorMessage(e);

        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(
                e.getStatusCode(),
                "올바르지 않은 입력입니다."
        );

        problemDetail.setProperties(message);
        problemDetail.setTitle(e.getNestedPath());

        return problemDetail;
    }


    private HashMap<String, Object> getErrorMessage(MethodArgumentNotValidException e) {

        //1. Valid로 검증한 모든 에러들
        List<ObjectError> allErrors = e.getBindingResult().getAllErrors();
        HashMap<String, Object> map = new HashMap<>();

        //2. 에러들 각각 map에 (key : 필드, value : message) 담기
        allErrors.forEach(error -> {

            if (error instanceof FieldError fieldError) {
                String field = fieldError.getField();
                String message = fieldError.getDefaultMessage();

                map.put(field, message);
            }
        });

        return map;
    }

}

