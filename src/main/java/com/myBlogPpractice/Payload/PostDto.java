package com.myBlogPpractice.Payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class PostDto {
    private  long id;
    @NotEmpty
    @Size(min = 2,message = "title should have 2 characters")
    private String title;

    @NotEmpty
    @Size(min = 10,message = "description should have 10 characters")
    private String description;
    @NotEmpty
    private String content;


}
