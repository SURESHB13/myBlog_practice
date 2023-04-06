package com.myBlogPpractice.service;

import com.myBlogPpractice.Payload.CommentDto;

public interface CommentService {
    CommentDto createComment(long postId, CommentDto commentDto);
}
