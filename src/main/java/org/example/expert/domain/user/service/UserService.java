package org.example.expert.domain.user.service;

import lombok.RequiredArgsConstructor;
import org.example.expert.common.dto.CommonResponse;
import org.example.expert.common.enums.ExceptionCode;
import org.example.expert.common.util.passworencoder.PasswordEncoder;
import org.example.expert.domain.user.dto.request.UserChangePasswordRequest;
import org.example.expert.domain.user.dto.response.UserResponse;
import org.example.expert.domain.user.entity.User;
import org.example.expert.domain.user.exception.UserException;
import org.example.expert.domain.user.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional(readOnly = true)
    public CommonResponse<UserResponse> getUser(long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new UserException(ExceptionCode.NOT_FOUND_USER));

        return new CommonResponse<>(HttpStatus.OK, new UserResponse(user.getId(), user.getEmail()));
    }

    @Transactional
    public CommonResponse<Void> changePassword(long userId, UserChangePasswordRequest userChangePasswordRequest) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserException(ExceptionCode.NOT_FOUND_USER));

        if (passwordEncoder.matches(userChangePasswordRequest.getNewPassword(), user.getPassword())) {
            throw new UserException(ExceptionCode.NOT_MATCH_OLD_PASSWORD_NEW_PASSWORD);
        }

        if (!passwordEncoder.matches(userChangePasswordRequest.getOldPassword(), user.getPassword())) {
            throw new UserException(ExceptionCode.MISS_MATCHES_PASSWORD);
        }

        user.changePassword(passwordEncoder.encode(userChangePasswordRequest.getNewPassword()));

        return new CommonResponse<>(HttpStatus.NO_CONTENT, null);
    }
}
