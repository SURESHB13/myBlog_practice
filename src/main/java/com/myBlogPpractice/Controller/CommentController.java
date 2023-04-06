package com.myBlogPpractice.Controller;

import com.myBlogPpractice.Payload.CommentDto;
import com.myBlogPpractice.service.CommentService;
import com.myBlogPpractice.service.PostService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/")
public class CommentController {
    private CommentService commentService;
    private PostService postService;

    public CommentController(CommentService commentService, PostService postService) {
        this.commentService = commentService;

        this.postService = postService;
    }
//http://localhost:8096/api/{postId}
    @PostMapping("/posts/{postId}/comments")
    public ResponseEntity<?>createComment(@Valid @PathVariable(value = "postId") Long postId, @RequestBody CommentDto commentDto, BindingResult result){
        if (result.hasErrors()){
            return new ResponseEntity<>(result.getFieldError().getDefaultMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
return new ResponseEntity<CommentDto>(commentService.createComment(postId,commentDto), HttpStatus.CREATED);

    }
    @GetMapping("/posts/{postId}/comments")
public List<CommentDto>getCommentsByPostId(@PathVariable("postId")Long postId){
    return    commentService.getCommentsByPostId(postId);
}
    @GetMapping("/posts/{postId}/comments/{commentId}")

    public CommentDto getCommentByCommentId(@PathVariable("postId")Long postId,@PathVariable("commentId")Long commentId){
      return   commentService.getCommentByCommentId(postId,commentId);
    }
    //http://localhost:8096/api/posts/5/comments/5
    @PutMapping("/posts/{postId}/comments/{commentId}")
    public  ResponseEntity<?>updateComment(@Valid @PathVariable("postId")Long postId,
                                                    @PathVariable("commentId")Long commentId,
    @RequestBody CommentDto commentDto,
                                                    BindingResult result
    ){
        if (result.hasErrors()){
            return new ResponseEntity<>(result.getFieldError().getDefaultMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
     CommentDto commentDto1=commentService.updateComment(postId,commentId,commentDto);
     return new ResponseEntity<>(commentDto1,HttpStatus.ACCEPTED);

    }
@DeleteMapping("/posts/{postId}/comments/{commentId}")
    public ResponseEntity<String> deleteComment(@PathVariable long postId, @PathVariable long commentId){
        commentService.deleteComment(postId,commentId);
        return new ResponseEntity<String>("Deleted the comment successfully!!!",HttpStatus.OK);
    }


}

