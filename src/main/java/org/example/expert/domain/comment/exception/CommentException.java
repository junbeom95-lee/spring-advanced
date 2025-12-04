package org.example.expert.domain.comment.exception;

import org.example.expert.common.enums.ExceptionCode;
import org.example.expert.common.exception.ServerException;

public class CommentException extends ServerException {

    public CommentException(ExceptionCode exceptionCode) {
        super(exceptionCode);
    }
}
