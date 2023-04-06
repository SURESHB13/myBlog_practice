package com.myBlogPpractice.service;

import com.myBlogPpractice.Payload.CommentDto;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface CommentService {
    CommentDto createComment(long postId, CommentDto commentDto);

    List<CommentDto> getCommentsByPostId(Long postId);

    CommentDto getCommentByCommentId(Long postId, Long commentId);

    CommentDto updateComment(Long postId, Long commentId, CommentDto commentDto);
}
