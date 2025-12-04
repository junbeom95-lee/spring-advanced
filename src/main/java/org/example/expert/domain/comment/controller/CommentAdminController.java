package org.example.expert.domain.comment.controller;

import lombok.RequiredArgsConstructor;
import org.example.expert.common.dto.CommonResponse;
import org.example.expert.domain.comment.service.CommentAdminService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class CommentAdminController {

    private final CommentAdminService commentAdminService;

    @DeleteMapping("/admin/comments/{commentId}")
    public ResponseEntity<CommonResponse<Void>> deleteComment(@PathVariable long commentId) {

        CommonResponse<Void> result = commentAdminService.deleteComment(commentId);

        return ResponseEntity.status(result.getStatus()).body(result);
    }
}
