package com.myBlogPpractice.Entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(
        name = "post", uniqueConstraints = {@UniqueConstraint(columnNames =
        {"title"})}
)
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private  String Content;
    private String description;
    @OneToMany
            (mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval =
                    true)
    private Set<Comment> comments = new HashSet<>();
}
