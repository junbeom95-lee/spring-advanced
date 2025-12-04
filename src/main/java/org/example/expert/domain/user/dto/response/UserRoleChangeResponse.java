package org.example.expert.domain.user.dto.response;

import lombok.Getter;

@Getter
public class UserRoleChangeResponse {

    private final Long id;
    private final String email;
    private final String userRole;

    public UserRoleChangeResponse(Long id, String email, String userRole) {
        this.id = id;
        this.email = email;
        this.userRole = userRole;
    }
}
