package com.main.blogs.blog.service;

import com.main.blogs.blog.DTO.BlogDTO;
import com.main.blogs.blog.model.Blog;
import com.main.blogs.blog.repository.BlogRepository;
import com.main.blogs.blog.util.BlogMapper;
import com.main.blogs.exception.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.UUID;

import static com.main.blogs.blog.util.BlogMapper.mapToDto;

@Service
public class BlogService {

    private final BlogRepository blogRepository;

    public BlogService(BlogRepository blogRepository) {
        this.blogRepository = blogRepository;
    }


    public BlogDTO create(BlogDTO newBlog) {
        Blog blog = new Blog();
        blog.setTitle(newBlog.getTitle());
        blog.setContent(newBlog.getContent());
        blog.setCreatedAt(LocalDateTime.now());
        blog.setUpdatedAt(LocalDateTime.now());
        blog.setComments(new HashSet<>());
        blogRepository.save(blog);
        return mapToDto(blog);
    }

    public List<BlogDTO> getBlogs() {
        List<Blog> blogs = blogRepository.findAll();
        return blogs.stream().map(BlogMapper::mapToDto).toList();
    }

    public Blog getBlogById(UUID blogId) {
        return blogRepository.findById(blogId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Blog with id: " + blogId + " not found"));
    }

    public BlogDTO getBlogDtoById(UUID blogId) {
        Blog blog = blogRepository.findById(blogId).orElseThrow(() ->
                new ResourceNotFoundException(
                        "Blog with id: " + blogId + " not found"));
        return mapToDto(blog);
    }

    public BlogDTO update(UUID blogId, BlogDTO updatedBlog) {
        Blog blog = blogRepository.findById(blogId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Blog with id: " + blogId + " not found"));
        blog.setTitle(updatedBlog.getTitle());
        blog.setContent(updatedBlog.getContent());
        blog.setUpdatedAt(LocalDateTime.now());
        blogRepository.save(blog);
        return mapToDto(blog);
    }

    public void delete(UUID blogId) {
        Blog blog = blogRepository.findById(blogId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Blog with id: " + blogId + " not found"));
        blogRepository.delete(blog);
    }
}
