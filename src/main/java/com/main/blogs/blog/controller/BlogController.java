package com.main.blogs.blog.controller;

import com.main.blogs.blog.DTO.BlogDTO;
import com.main.blogs.blog.service.BlogService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Validated
@RestController
@RequestMapping("/api/v1/blogs")
public class BlogController {
    private final BlogService blogService;

    public BlogController(BlogService blogService) {
        this.blogService = blogService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<BlogDTO> getBlogs() {
        return blogService.getBlogs();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public BlogDTO getBlog(@Valid @PathVariable UUID id) {
        return blogService.getBlogDtoById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public BlogDTO createBlog(
            @Validated(BlogDTO.CreateBlogDTO.class) @RequestBody
            BlogDTO blogDTO) {
        return blogService.create(blogDTO);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public BlogDTO updateBlog(@Valid @PathVariable UUID id,
                              @Validated(BlogDTO.UpdateBlogDTO.class)
                              @RequestBody BlogDTO blogDTO) {
        return blogService.update(id, blogDTO);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteBlog(@Valid @PathVariable UUID id) {
        blogService.delete(id);
    }
}
