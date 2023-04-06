package com.myBlogPpractice.service;

import com.myBlogPpractice.Payload.PostDto;

import java.util.List;

public interface PostService {
    PostDto createPost(PostDto postDto);

    List<PostDto> getAllPosts();

    PostDto updatePost(PostDto postDto,Long id);
}
