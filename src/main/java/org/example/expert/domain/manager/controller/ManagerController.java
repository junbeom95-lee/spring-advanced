package org.example.expert.domain.manager.controller;

import io.jsonwebtoken.Claims;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.expert.common.dto.CommonResponse;
import org.example.expert.common.util.jwt.JwtUtil;
import org.example.expert.common.annotation.Auth;
import org.example.expert.common.dto.AuthUser;
import org.example.expert.domain.manager.dto.request.ManagerSaveRequest;
import org.example.expert.domain.manager.dto.response.ManagerResponse;
import org.example.expert.domain.manager.dto.response.ManagerSaveResponse;
import org.example.expert.domain.manager.service.ManagerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ManagerController {

    private final ManagerService managerService;
    private final JwtUtil jwtUtil;

    @PostMapping("/todos/{todoId}/managers")
    public ResponseEntity<CommonResponse<ManagerSaveResponse>> saveManager(
            @Auth AuthUser authUser,
            @PathVariable long todoId,
            @Valid @RequestBody ManagerSaveRequest managerSaveRequest
    ) {
        CommonResponse<ManagerSaveResponse> result = managerService.saveManager(authUser, todoId, managerSaveRequest);

        return ResponseEntity.status(result.getStatus()).body(result);
    }

    @GetMapping("/todos/{todoId}/managers")
    public ResponseEntity<CommonResponse<List<ManagerResponse>>> getMembers(@PathVariable long todoId) {

        CommonResponse<List<ManagerResponse>> result = managerService.getManagers(todoId);

        return ResponseEntity.status(result.getStatus()).body(result);
    }

    @DeleteMapping("/todos/{todoId}/managers/{managerId}")
    public ResponseEntity<CommonResponse<Void>> deleteManager(
            @RequestHeader("Authorization") String bearerToken,
            @PathVariable long todoId,
            @PathVariable long managerId
    ) {
        Claims claims = jwtUtil.extractClaims(bearerToken.substring(7));

        long userId = Long.parseLong(claims.getSubject());

        CommonResponse<Void> result = managerService.deleteManager(userId, todoId, managerId);

        return ResponseEntity.status(result.getStatus()).body(result);
    }
}
