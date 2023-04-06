package com.myBlogPpractice.service;

import com.myBlogPpractice.Payload.PostDto;
import com.myBlogPpractice.service.impl.PostResponse;

import java.util.List;

public interface PostService {
    PostDto createPost(PostDto postDto);

    List<PostDto> getAllPosts();

    PostDto updatePost(PostDto postDto,Long id);

    PostDto getPostById(Long id);

    void deleteById(long id);

    PostResponse getAllThroughPagination(int pageNo, int pageSize, String sortBy, String sortDir);
}
