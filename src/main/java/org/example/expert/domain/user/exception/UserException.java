package org.example.expert.domain.user.exception;

import org.example.expert.common.enums.ExceptionCode;
import org.example.expert.common.exception.ServerException;

public class UserException extends ServerException {

    public UserException(ExceptionCode exceptionCode) {
        super(exceptionCode);
    }
}
