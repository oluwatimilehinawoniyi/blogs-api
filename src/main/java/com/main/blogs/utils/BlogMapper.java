package com.main.blogs.utils;

import com.main.blogs.DTO.BlogDTO;
import com.main.blogs.model.Blog;

import java.util.UUID;

public class BlogMapper {
    public static BlogDTO mapToDto(Blog blog) {
        BlogDTO dto = new BlogDTO();
        dto.setId(blog.getId().toString());
        dto.setTitle(blog.getTitle());
        dto.setContent(blog.getContent());
        dto.setCreatedAt(blog.getCreatedAt());
        dto.setUpdatedAt(blog.getUpdatedAt());
        return dto;
    }

    public static Blog mapToEntity(BlogDTO dto) {
        Blog blog = new Blog();
        blog.setId(UUID.fromString(dto.getId()));
        blog.setTitle(dto.getTitle());
        blog.setContent(dto.getContent());
        blog.setCreatedAt(dto.getCreatedAt());
        blog.setUpdatedAt(dto.getUpdatedAt());
        blog.setComments(dto.getComments());
        return blog;
    }
}
