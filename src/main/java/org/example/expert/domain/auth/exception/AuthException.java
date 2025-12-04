package org.example.expert.domain.auth.exception;

import org.example.expert.common.enums.ExceptionCode;
import org.example.expert.common.exception.ServerException;

public class AuthException extends ServerException {

    public AuthException(ExceptionCode exceptionCode) {
        super(exceptionCode);
    }
}
