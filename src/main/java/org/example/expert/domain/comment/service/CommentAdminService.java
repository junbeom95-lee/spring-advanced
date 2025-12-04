package org.example.expert.domain.comment.service;

import lombok.RequiredArgsConstructor;
import org.example.expert.common.dto.CommonResponse;
import org.example.expert.domain.comment.repository.CommentRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CommentAdminService {

    private final CommentRepository commentRepository;

    @Transactional
    public CommonResponse<Void> deleteComment(long commentId) {

        commentRepository.deleteById(commentId);

        return new CommonResponse<>(HttpStatus.NO_CONTENT, null);
    }
}
