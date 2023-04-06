package com.myBlogPpractice.Controller;

import com.myBlogPpractice.Payload.PostDto;
import com.myBlogPpractice.service.PostService;
import com.myBlogPpractice.service.impl.PostResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/posts/")
public class PostController {
    private PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

//http://localhost:8096/api/posts/
    @PostMapping
    public ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto){
        return new ResponseEntity<>(postService.createPost(postDto), HttpStatus.CREATED);
    }
@GetMapping
    public  ResponseEntity<List<PostDto>>GetAllPosts(){
        return new ResponseEntity<>(postService.getAllPosts(),HttpStatus.OK);
}
//http://localhost:8096/api/posts/{id}
@PutMapping("/{id}")
    public ResponseEntity<PostDto>updatePost(@RequestBody PostDto postDto,@PathVariable("id") long id){
        return new ResponseEntity<>(postService.updatePost(postDto,id),HttpStatus.ACCEPTED);
}

@GetMapping("/{id}")
    public  ResponseEntity<PostDto>getPostById(@PathVariable("id")Long id){
        return  new ResponseEntity<>(postService.getPostById(id),HttpStatus.OK);
}

@DeleteMapping("/{id}")
    public ResponseEntity<String> deleteMapping(@PathVariable long id){
        postService.deleteById(id);
        return new ResponseEntity<>("deleted successfully",HttpStatus.OK);
}
@GetMapping("/pagination")
    public PostResponse getAllThroughPagination(

        @RequestParam(value = "pageNo",defaultValue = "10",required = false)int pageNo,
        @RequestParam(value = "pageSize",defaultValue = "10",required = false)int pageSize,
        @RequestParam(value = "sortBy",defaultValue = "id",required = false)String sortBy,
        @RequestParam(value = "sortDir",defaultValue = "asc",required = false)String sortDir
){
PostResponse postResponse=postService.getAllThroughPagination(pageNo,pageSize,sortBy,sortDir);
return  postResponse;
}

}
