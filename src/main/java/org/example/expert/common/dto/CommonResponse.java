package org.example.expert.common.dto;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class CommonResponse<T> {

    private final HttpStatus status;
    private final int code;
    private final T content;

    public CommonResponse(HttpStatus status, T content) {
        this.status = status;
        this.code = status.value();
        this.content = content;
    }
}
