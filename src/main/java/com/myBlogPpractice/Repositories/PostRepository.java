package com.myBlogPpractice.Repositories;

import com.myBlogPpractice.Entities.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post,Long> {
}
