package com.main.blogs.service;

import com.main.blogs.DTO.CommentDTO;
import com.main.blogs.model.Blog;
import com.main.blogs.model.Comment;
import com.main.blogs.repository.BlogRepository;
import com.main.blogs.repository.CommentRepository;
import com.main.blogs.utils.CommentMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class CommentServices {

    private final CommentRepository commentRepository;
    private final BlogServices blogServices;
    private final BlogRepository blogRepository;

    public CommentServices(CommentRepository commentRepository,
                           BlogServices blogServices,
                           BlogRepository blogRepository) {
        this.commentRepository = commentRepository;
        this.blogServices = blogServices;
        this.blogRepository = blogRepository;
    }

    public void addComment(UUID blogId, CommentDTO commentDto) {
        Blog blog = blogServices.getBlogById(blogId);

        Comment comment = new Comment();
        comment.setComment(commentDto.getComment());
        comment.setBlog(blog);
        comment.setCreatedAt(LocalDateTime.now());
        comment.setUpdatedAt(LocalDateTime.now());
        blog.getComments().add(comment);
        blogRepository.save(blog);
    }

    public CommentDTO getComment(UUID blogId, Long commentId) {
        Comment comment =
                commentRepository.findById(commentId).orElseThrow();
        if (!comment.getBlog().getId().equals(blogId)) {
            throw new IllegalArgumentException("Comment not found");
        }
        return CommentMapper.mapToDto(comment);
    }

    public List<CommentDTO> getCommentsForBlog(UUID blogId) {
        Blog blog = blogServices.getBlogById(blogId);

        List<Comment> comments =
                commentRepository.findAllByBlogId(blog.getId());
        return comments.stream().map(CommentMapper::mapToDto).toList();
    }

    public CommentDTO updateComment(UUID blogId, Long commentId,
                                    CommentDTO updatedComment) {
        Comment comment =
                commentRepository.findById(commentId).orElseThrow();
        if (!comment.getBlog().getId().equals(blogId)) {
            throw new IllegalArgumentException("Comment not found");
        }
        comment.setComment(updatedComment.getComment());
        comment.setUpdatedAt(LocalDateTime.now());
        commentRepository.save(comment);
        return CommentMapper.mapToDto(comment);
    }

    public void deleteComment(UUID blogId,Long commentId) {
        Comment comment =
                commentRepository.findById(commentId).orElseThrow();
        if (!comment.getBlog().getId().equals(blogId)) {
            throw new IllegalArgumentException("Comment not found");
        }
        commentRepository.deleteById(commentId);
    }
}
