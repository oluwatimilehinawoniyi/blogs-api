package com.main.blogs.comment.service;

import com.main.blogs.comment.DTO.CommentDTO;
import com.main.blogs.blog.model.Blog;
import com.main.blogs.blog.service.BlogService;
import com.main.blogs.comment.model.Comment;
import com.main.blogs.blog.repository.BlogRepository;
import com.main.blogs.comment.repository.CommentRepository;
import com.main.blogs.comment.utils.CommentMapper;
import com.main.blogs.exception.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class CommentService {

    private final CommentRepository commentRepository;
    private final BlogService blogService;
    private final BlogRepository blogRepository;

    public CommentService(CommentRepository commentRepository,
                          BlogService blogService,
                          BlogRepository blogRepository) {
        this.commentRepository = commentRepository;
        this.blogService = blogService;
        this.blogRepository = blogRepository;
    }

    public void addComment(UUID blogId, CommentDTO commentDto) {
        Blog blog = blogService.getBlogById(blogId);

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
                commentRepository.findById(commentId)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Comment with id: " + commentId +
                                                " not found"));
        if (!comment.getBlog().getId().equals(blogId)) {
            throw new ResourceNotFoundException(String.format(
                    "Comment [%d] does not belong to Blog [%s]", commentId,
                    blogId)
            );
        }
        return CommentMapper.mapToDto(comment);
    }

    public List<CommentDTO> getCommentsForBlog(UUID blogId) {
        Blog blog = blogService.getBlogById(blogId);

        List<Comment> comments =
                commentRepository.findAllByBlogId(blog.getId());
        return comments.stream().map(CommentMapper::mapToDto).toList();
    }

    public CommentDTO updateComment(UUID blogId, Long commentId,
                                    CommentDTO updatedComment) {
        Comment comment =
                commentRepository.findById(commentId).orElseThrow(() ->
                        new ResourceNotFoundException("Comment with id:" +
                                commentId + " not found"));

        if (!comment.getBlog().getId().equals(blogId)) {
            throw new ResourceNotFoundException(String.format(
                    "Comment [%d] does not belong to Blog [%s]", commentId,
                    blogId));
        }
        comment.setComment(updatedComment.getComment());
        comment.setUpdatedAt(LocalDateTime.now());
        commentRepository.save(comment);
        return CommentMapper.mapToDto(comment);
    }

    public void deleteComment(UUID blogId, Long commentId) {
        Comment comment =
                commentRepository.findById(commentId).orElseThrow(() ->
                        new ResourceNotFoundException("Comment with id:" +
                                commentId + " not found"));

        if (!comment.getBlog().getId().equals(blogId)) {
            throw new ResourceNotFoundException(String.format(
                    "Comment [%d] does not belong to Blog [%s]", commentId,
                    blogId));
        }
        commentRepository.deleteById(commentId);
    }
}
