package org.example.expert.domain.user.service;

import lombok.RequiredArgsConstructor;
import org.example.expert.common.dto.CommonResponse;
import org.example.expert.common.enums.ExceptionCode;
import org.example.expert.domain.user.dto.request.UserRoleChangeRequest;
import org.example.expert.domain.user.dto.response.UserRoleChangeResponse;
import org.example.expert.domain.user.entity.User;
import org.example.expert.domain.user.enums.UserRole;
import org.example.expert.domain.user.exception.UserException;
import org.example.expert.domain.user.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserAdminService {

    private final UserRepository userRepository;

    @Transactional
    public CommonResponse<UserRoleChangeResponse> changeUserRole(long userId, UserRoleChangeRequest userRoleChangeRequest) {

        User user = userRepository.findById(userId).orElseThrow(() -> new UserException(ExceptionCode.NOT_FOUND_USER));

        user.updateRole(UserRole.of(userRoleChangeRequest.getRole()));

        UserRoleChangeResponse dto = new UserRoleChangeResponse(user.getId(), user.getEmail(), user.getUserRole().toString());

        return new CommonResponse<>(HttpStatus.OK, dto);
    }
}
