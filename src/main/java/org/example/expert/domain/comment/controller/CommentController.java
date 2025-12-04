package org.example.expert.domain.comment.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.expert.common.dto.CommonResponse;
import org.example.expert.domain.comment.dto.request.CommentSaveRequest;
import org.example.expert.domain.comment.dto.response.CommentResponse;
import org.example.expert.domain.comment.dto.response.CommentSaveResponse;
import org.example.expert.domain.comment.service.CommentService;
import org.example.expert.common.annotation.Auth;
import org.example.expert.common.dto.AuthUser;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @PostMapping("/todos/{todoId}/comments")
    public ResponseEntity<CommonResponse<CommentSaveResponse>> saveComment(
            @Auth AuthUser authUser,
            @PathVariable long todoId,
            @Valid @RequestBody CommentSaveRequest commentSaveRequest
    ) {

        CommonResponse<CommentSaveResponse> result = commentService.saveComment(authUser, todoId, commentSaveRequest);

        return ResponseEntity.status(result.getStatus()).body(result);
    }

    @GetMapping("/todos/{todoId}/comments")
    public ResponseEntity<CommonResponse<List<CommentResponse>>> getComments(@PathVariable long todoId) {

        CommonResponse<List<CommentResponse>> result = commentService.getComments(todoId);

        return ResponseEntity.status(result.getStatus()).body(result);
    }
}
