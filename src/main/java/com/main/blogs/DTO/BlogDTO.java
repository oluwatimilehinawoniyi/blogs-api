package com.main.blogs.DTO;

import com.main.blogs.model.Comment;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Set;

@Data
public class BlogDTO {

    public interface CreateBlogDTO{}
    public interface GetBlogDTO{}
    public interface UpdateBlogDTO{}

    @NotBlank
    @Null(groups = {CreateBlogDTO.class, UpdateBlogDTO.class})
    @NotNull(groups = GetBlogDTO.class)
    private String id;

    @NotBlank(groups = {CreateBlogDTO.class, GetBlogDTO.class})
    @Size(min = 5, max = 200)
    private String title;

    @NotBlank(groups = {CreateBlogDTO.class, GetBlogDTO.class})
    @Size(min = 10, max = 1000)
    private String content;

    @Null(groups = CreateBlogDTO.class)
    @NotNull(groups = GetBlogDTO.class)
    private LocalDateTime createdAt;

    @Null(groups = CreateBlogDTO.class)
    @NotNull(groups = GetBlogDTO.class)
    private LocalDateTime updatedAt;

    @Null(groups = CreateBlogDTO.class)
    @NotNull(groups = GetBlogDTO.class)
    private Set<CommentDTO> comments;
}
