package com.myBlogPpractice.service.impl;

import com.myBlogPpractice.Entities.Post;
import com.myBlogPpractice.Exception.ResourceNotFoundException;
import com.myBlogPpractice.Payload.PostDto;
import com.myBlogPpractice.Repositories.PostRepository;
import com.myBlogPpractice.service.PostService;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {
    private PostRepository postRepository;
    private ModelMapper modelMapper;

    public PostServiceImpl(PostRepository postRepository, ModelMapper modelMapper) {
        this.postRepository = postRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public PostDto createPost(PostDto postDto) {
        Post post = modelMapper.map(postDto, Post.class);
        Post savedPost = postRepository.save(post);
       return modelMapper.map(savedPost, PostDto.class);
    }

    @Override
    public List<PostDto> getAllPosts() {
        List<Post> all = postRepository.findAll();
        List<PostDto> collect = all.stream().map(post -> modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
return  collect;

    }

    @Override
    public PostDto updatePost(PostDto postdto, Long id) {
        Post post = postRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("post", "id", id)
        );
        post.setContent(postdto.getContent());
        post.setDescription(postdto.getDescription());
        post.setTitle(postdto.getTitle());
        Post updatedPost = postRepository.save(post);
       return modelMapper.map(updatedPost,PostDto.class);
    }

    @Override
    public PostDto getPostById(Long id) {
        Post post = postRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("post", "id", id)
        );
        return modelMapper.map(post,PostDto.class);
    }

    @Override
    public void deleteById(long id) {
        Post post = postRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("post", "id", id)
        );
        postRepository.deleteById(id);
    }

    @Override
    public PostResponse getAllThroughPagination(int pageNo, int pageSize, String sortBy, String sortDir) {
        Sort sort=sortDir.equalsIgnoreCase(Sort.Direction.ASC.name())?
                Sort.by(sortBy).ascending():
                Sort.by(sortBy).descending();
        PageRequest pageable=PageRequest.of(pageNo,pageSize,sort);
        Page<Post> content = postRepository.findAll(pageable);
        List<Post> posts = content.getContent();
        List<PostDto> dto = posts.stream().map(post -> modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
PostResponse postResponse=new PostResponse();
postResponse.setContent(dto);
postResponse.setPageNo(content.getNumber() );
postResponse.setPageSize(content.getSize());
postResponse.setTotalPages(content.getTotalPages());
postResponse.setTotalElements((int)content.getTotalElements());
postResponse.setLast(content.isLast());
return postResponse;

    }


}
