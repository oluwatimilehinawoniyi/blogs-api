package com.main.blogs.comment.DTO;

import jakarta.validation.constraints.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CommentDTO {
    public interface CreateCommentDTO {
    }

    public interface GetCommentDTO {
    }

    public interface UpdateCommentDTO {
    }

    public interface DeleteCommentDTO {
    }

    @Null(groups = {CreateCommentDTO.class})
    @NotNull(groups = GetCommentDTO.class)
    private Long id;

    @NotBlank(groups = {CreateCommentDTO.class, UpdateCommentDTO.class})
    @Size(min = 5, max = 200)
    private String comment;

    @Null(groups = CreateCommentDTO.class)
    @NotNull(groups = {GetCommentDTO.class, UpdateCommentDTO.class})
    private LocalDateTime createdAt;

    @Null(groups = CreateCommentDTO.class)
    @NotNull(groups = {GetCommentDTO.class, UpdateCommentDTO.class})
    private LocalDateTime updatedAt;
}
