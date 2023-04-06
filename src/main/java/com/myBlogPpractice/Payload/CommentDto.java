package com.myBlogPpractice.Payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentDto {

    private long id;
    @NotEmpty
    @Size(min=3,message = "name should have at least 3 characters")
    private String name;
    @Email
    private String email;
    @NotEmpty
    @Size(min=10,message = "body off the comment should have at least 10 characters")
    private String body;



}
