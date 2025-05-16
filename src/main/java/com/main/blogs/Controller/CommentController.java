package com.main.blogs.Controller;

import com.main.blogs.DTO.CommentDTO;
import com.main.blogs.service.CommentServices;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/blogs")
public class CommentController {

    private final CommentServices commentServices;

    public CommentController(CommentServices commentServices) {
        this.commentServices = commentServices;
    }

    @PostMapping("/{blogId}/comments")
    public void createComment(@PathVariable UUID blogId,
                              @RequestBody CommentDTO comment) {
        commentServices.addComment(blogId, comment);
    }

    @GetMapping("/{blogId}/comments")
    public List<CommentDTO> getComments(@PathVariable UUID blogId) {
        return commentServices.getCommentsForBlog(blogId);
    }

    @GetMapping("/{blogId}/comments/{commentId}")
    public CommentDTO getComment(@PathVariable UUID blogId,
                                 @PathVariable Long commentId) {
        return commentServices.getComment(blogId, commentId);
    }

    @PutMapping("/{blogId}/comments/{commentId}")
    public CommentDTO updateComment(@PathVariable UUID blogId,
                                    @PathVariable Long commentId,
                                    @RequestBody CommentDTO comment) {
        return commentServices.updateComment(blogId, commentId, comment);
    }

    @DeleteMapping("/{blogId}/comments/{commentId}")
    public void deleteComment(@PathVariable UUID blogId,
                              @PathVariable Long commentId) {
        commentServices.deleteComment(blogId, commentId);
    }
}
