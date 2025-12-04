package org.example.expert.common.exception;

import lombok.Getter;
import org.example.expert.common.enums.ExceptionCode;

@Getter
public class ServerException extends RuntimeException {

    private final ExceptionCode exceptionCode;

    public ServerException(ExceptionCode exceptionCode) {
        super(exceptionCode.getMessage());
        this.exceptionCode = exceptionCode;
    }
}
