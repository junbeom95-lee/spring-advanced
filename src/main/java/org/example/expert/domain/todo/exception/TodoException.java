package org.example.expert.domain.todo.exception;

import org.example.expert.common.enums.ExceptionCode;
import org.example.expert.common.exception.ServerException;

public class TodoException extends ServerException {

    public TodoException(ExceptionCode exceptionCode) {
        super(exceptionCode);
    }
}
