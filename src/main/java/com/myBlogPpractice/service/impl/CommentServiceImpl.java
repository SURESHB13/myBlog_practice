package com.myBlogPpractice.service.impl;

import com.myBlogPpractice.Entities.Comment;
import com.myBlogPpractice.Entities.Post;
import com.myBlogPpractice.Exception.BlogAPIException;
import com.myBlogPpractice.Exception.ResourceNotFoundException;
import com.myBlogPpractice.Payload.CommentDto;
import com.myBlogPpractice.Repositories.CommentRepository;
import com.myBlogPpractice.Repositories.PostRepository;
import com.myBlogPpractice.service.CommentService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
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

    @Override
    public CommentDto getCommentByCommentId(Long postId, Long commentId) {
        Post post = postRepository.findById(postId).orElseThrow(
                () -> new ResourceNotFoundException("post", "id", postId)
        );
        Comment comment = commentRepository.findById(commentId).orElseThrow(
                () -> new ResourceNotFoundException("comment", "id", commentId)
        );
        if(!comment.getPost().getId().equals(post.getId())){
            throw new BlogAPIException(HttpStatus.BAD_REQUEST,"Comment does not belong to post");
        }
        return modelMapper.map(comment,CommentDto.class);
    }

    @Override
    public CommentDto updateComment(Long postId, Long commentId, CommentDto commentDto) {
        Post post = postRepository.findById(postId).orElseThrow(
                () -> new ResourceNotFoundException("post", "id", postId)
        );
        Comment comment = commentRepository.findById(commentId).orElseThrow(
                () -> new ResourceNotFoundException("comment", "id", commentId)
        );
        if (!comment.getPost().getId().equals(post.getId()))
        {
throw new BlogAPIException(HttpStatus.BAD_REQUEST,"comment does not belong to the post with given PostId");
        }
        comment.setName(commentDto.getName());
        comment.setEmail(commentDto.getEmail());
        comment.setBody(commentDto.getBody());
        Comment savedComment = commentRepository.save(comment);
        return  modelMapper.map(savedComment,CommentDto.class);
    }

    @Override
    public void deleteComment(long postId, long commentId) {
        Post post = postRepository.findById(postId).orElseThrow(
                () -> new ResourceNotFoundException("Post", "id", postId)
        );
        Comment comment = commentRepository.findById(commentId).orElseThrow(
                () -> new ResourceNotFoundException("comment", "id", commentId)
        );
        if (!comment.getPost().getId().equals(post.getId()))
        {
            throw new BlogAPIException(HttpStatus.BAD_REQUEST,"comment not belongs to the post with a given id by you");
        }
        commentRepository.deleteById(commentId);
    }
    public ResponseEntity<Post> getPostById(Long postId) {
        Optional<Post> post = postRepository.findById(postId);
        return post.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }


}
