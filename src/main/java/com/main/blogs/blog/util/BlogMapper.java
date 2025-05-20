package com.main.blogs.blog.util;

import com.main.blogs.blog.DTO.BlogDTO;
import com.main.blogs.comment.DTO.CommentDTO;
import com.main.blogs.blog.model.Blog;
import com.main.blogs.comment.model.Comment;
import com.main.blogs.comment.utils.CommentMapper;

import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

public class BlogMapper {
    public static BlogDTO mapToDto(Blog blog) {
        BlogDTO dto = new BlogDTO();
        dto.setId(blog.getId().toString());
        dto.setTitle(blog.getTitle());
        dto.setContent(blog.getContent());
        dto.setCreatedAt(blog.getCreatedAt());
        dto.setUpdatedAt(blog.getUpdatedAt());

        Set<CommentDTO> comments = blog.getComments()
                .stream().map(CommentMapper::mapToDto)
                .collect(Collectors.toSet());
        dto.setComments(comments);
        return dto;
    }

    public static Blog mapToEntity(BlogDTO dto) {
        Blog blog = new Blog();
        blog.setId(UUID.fromString(dto.getId()));
        blog.setTitle(dto.getTitle());
        blog.setContent(dto.getContent());
        blog.setCreatedAt(dto.getCreatedAt());
        blog.setUpdatedAt(dto.getUpdatedAt());

        Set<Comment> comments =
                dto.getComments().stream().map(CommentMapper::mapToEntity)
                        .collect(Collectors.toSet());
        blog.setComments(comments);
        return blog;
    }
}
