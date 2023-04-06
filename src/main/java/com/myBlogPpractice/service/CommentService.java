package com.myBlogPpractice.service;

import com.myBlogPpractice.Payload.CommentDto;

import java.util.List;

public interface CommentService {
    CommentDto createComment(long postId, CommentDto commentDto);

    List<CommentDto> getCommentsByPostId(Long postId);
}
