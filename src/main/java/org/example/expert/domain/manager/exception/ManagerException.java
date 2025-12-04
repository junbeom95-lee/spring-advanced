package org.example.expert.domain.manager.exception;

import org.example.expert.common.enums.ExceptionCode;
import org.example.expert.common.exception.ServerException;

public class ManagerException extends ServerException {

    public ManagerException(ExceptionCode exceptionCode) {
        super(exceptionCode);
    }
}
