package com.main.blogs.Controller;

import com.main.blogs.DTO.BlogDTO;
import com.main.blogs.model.Blog;
import com.main.blogs.service.BlogServices;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/blogs")
public class BlogController {
    private final BlogServices blogServices;

    public BlogController(BlogServices blogServices) {
        this.blogServices = blogServices;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<BlogDTO> getBlogs() {
        return blogServices.getBlogs();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public BlogDTO getBlog(@Valid @PathVariable UUID id) {
        return blogServices.getBlogById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public BlogDTO createBlog(
            @Validated(BlogDTO.CreateBlogDTO.class) @RequestBody
            BlogDTO blogDTO) {
        return blogServices.create(blogDTO);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public BlogDTO updateBlog(@Valid @PathVariable UUID id,
                              @Validated(BlogDTO.UpdateBlogDTO.class)
                              @RequestBody BlogDTO blogDTO) {
        return blogServices.update(id, blogDTO);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteBlog(@Valid @PathVariable UUID id) {
        blogServices.delete(id);
    }
}
