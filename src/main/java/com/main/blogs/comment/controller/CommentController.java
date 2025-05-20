package com.main.blogs.comment.controller;

import com.main.blogs.comment.DTO.CommentDTO;
import com.main.blogs.comment.service.CommentService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/blogs")
public class CommentController {

    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping("/{blogId}/comments")
    public void createComment(@PathVariable UUID blogId,
                              @Validated(CommentDTO.CreateCommentDTO.class)
                              @RequestBody CommentDTO comment) {
        commentService.addComment(blogId, comment);
    }

    @GetMapping("/{blogId}/comments")
    public List<CommentDTO> getComments(@PathVariable UUID blogId) {
        return commentService.getCommentsForBlog(blogId);
    }

    @GetMapping("/{blogId}/comments/{commentId}")
    public CommentDTO getComment(@PathVariable UUID blogId,
                                 @PathVariable Long commentId) {
        return commentService.getComment(blogId, commentId);
    }

    @PutMapping("/{blogId}/comments/{commentId}")
    public CommentDTO updateComment(@PathVariable UUID blogId,
                                    @PathVariable Long commentId,
                                    @RequestBody CommentDTO comment) {
        return commentService.updateComment(blogId, commentId, comment);
    }

    @DeleteMapping("/{blogId}/comments/{commentId}")
    public void deleteComment(@PathVariable UUID blogId,
                              @PathVariable Long commentId) {
        commentService.deleteComment(blogId, commentId);
    }
}
