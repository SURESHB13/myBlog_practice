package com.myBlogPpractice.service.impl;

import com.myBlogPpractice.Entities.Comment;
import com.myBlogPpractice.Entities.Post;
import com.myBlogPpractice.Exception.ResourceNotFoundException;
import com.myBlogPpractice.Payload.CommentDto;
import com.myBlogPpractice.Repositories.CommentRepository;
import com.myBlogPpractice.Repositories.PostRepository;
import com.myBlogPpractice.service.CommentService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class CommentServiceImpl implements CommentService {
private CommentRepository commentRepository;
private PostRepository postRepository;
private ModelMapper modelMapper;

    public CommentServiceImpl(CommentRepository commentRepository, PostRepository postRepository, ModelMapper modelMapper) {
        this.commentRepository = commentRepository;
        this.postRepository = postRepository;
        this.modelMapper = modelMapper;
    }
    @Override
    public CommentDto createComment(long postId, CommentDto commentDto) {
        Comment comment = modelMapper.map(commentDto,Comment.class);
        Post post = postRepository.findById(postId).orElseThrow(
                () -> new ResourceNotFoundException("post","id",postId)
        );comment.setPost(post);
        Comment newComment = commentRepository.save(comment);



        return modelMapper.map(newComment,CommentDto.class);
    }

    @Override
    public List<CommentDto> getCommentsByPostId(Long postId) {
        List<Comment> comments = commentRepository.findByPostId(postId);
return comments.stream().map(comment ->
        modelMapper.map(comment,CommentDto.class)).collect(Collectors.toList());
    }


}
