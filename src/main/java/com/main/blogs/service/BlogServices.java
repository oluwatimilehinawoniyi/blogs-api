package com.main.blogs.service;

import com.main.blogs.DTO.BlogDTO;
import com.main.blogs.model.Blog;
import com.main.blogs.repository.BlogRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class BlogServices {

    private final BlogRepository blogRepository;

    public BlogServices(BlogRepository blogRepository) {
        this.blogRepository = blogRepository;
    }


    public BlogDTO create(BlogDTO newBlog) {
        Blog blog = new Blog();
        blog.setTitle(newBlog.getTitle());
        blog.setContent(newBlog.getContent());
        blog.setCreatedAt(LocalDateTime.now());
        blog.setUpdatedAt(LocalDateTime.now());
        blogRepository.save(blog);
        return mapToDto(blog);
    }

    public List<BlogDTO> getBlogs() {
        List<Blog> blogs = blogRepository.findAll();
        return blogs.stream().map(this::mapToDto).toList();
    }

    public BlogDTO getBlogById(UUID blogId) {
        Blog blog = blogRepository.findById(blogId).orElseThrow();
        return mapToDto(blog);
    }

    public BlogDTO update(UUID blogId, BlogDTO updatedBlog) {
        Blog blog = blogRepository.findById(blogId).orElseThrow();
        blog.setTitle(updatedBlog.getTitle());
        blog.setContent(updatedBlog.getContent());
        blog.setUpdatedAt(LocalDateTime.now());
        blogRepository.save(blog);
        return mapToDto(blog);
    }

    public void delete(UUID blogId) {
        blogRepository.deleteById(blogId);
    }

    private BlogDTO mapToDto(Blog blog) {
        BlogDTO dto = new BlogDTO();
        dto.setId(blog.getId().toString());
        dto.setTitle(blog.getTitle());
        dto.setContent(blog.getContent());
        dto.setCreatedAt(blog.getCreatedAt());
        dto.setUpdatedAt(blog.getUpdatedAt());
        return dto;
    }
}
