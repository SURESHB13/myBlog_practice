package com.myBlogPpractice.Payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostDto {
    private  long id;
    private String title;
    private String content;
    private String description;
}
