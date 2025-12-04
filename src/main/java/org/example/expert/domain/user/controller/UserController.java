package org.example.expert.domain.user.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.expert.common.annotation.Auth;
import org.example.expert.common.dto.AuthUser;
import org.example.expert.common.dto.CommonResponse;
import org.example.expert.domain.user.dto.request.UserChangePasswordRequest;
import org.example.expert.domain.user.dto.response.UserResponse;
import org.example.expert.domain.user.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/users/{userId}")
    public ResponseEntity<CommonResponse<UserResponse>> getUser(@PathVariable long userId) {

        CommonResponse<UserResponse> result = userService.getUser(userId);

        return ResponseEntity.status(result.getStatus()).body(result);
    }

    @PutMapping("/users")
    public ResponseEntity<CommonResponse<Void>> changePassword(@Auth AuthUser authUser, @RequestBody @Valid UserChangePasswordRequest userChangePasswordRequest) {

        CommonResponse<Void> result = userService.changePassword(authUser.getId(), userChangePasswordRequest);

        return ResponseEntity.status(result.getStatus()).body(result);
    }
}
