package org.example.expert.domain.user.enums;

import org.example.expert.common.enums.ExceptionCode;
import org.example.expert.domain.user.exception.UserException;

import java.util.Arrays;

public enum UserRole {
    ADMIN, USER;

    public static UserRole of(String role) {
        return Arrays.stream(UserRole.values())
                .filter(r -> r.name().equalsIgnoreCase(role))
                .findFirst()
                .orElseThrow(() -> new UserException(ExceptionCode.NOT_FOUND_USER_ROLE));
    }
}
