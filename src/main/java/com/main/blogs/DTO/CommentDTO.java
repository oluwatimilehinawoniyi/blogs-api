package com.main.blogs.DTO;

import jakarta.validation.constraints.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@NotNull
@NotBlank
public class CommentDTO {
    public interface CreateCommentDTO{}
    public interface GetCommentDTO{}
    public interface UpdateCommentDTO{}
    public interface DeleteCommentDTO{}

    private Long id;
    private String comment;
    private String blogId;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
