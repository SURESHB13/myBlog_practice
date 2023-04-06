package com.myBlogPpractice.Repositories;

import com.myBlogPpractice.Entities.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment,Long> {

}
