package com.main.blogs.comment.repository;

import com.main.blogs.comment.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findAllByBlogId(UUID blogId);
}
