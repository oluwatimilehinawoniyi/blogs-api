package com.main.blogs.utils;

import com.main.blogs.DTO.CommentDTO;
import com.main.blogs.model.Comment;

public class CommentMapper {
    public static CommentDTO mapToDto(Comment comment) {
        CommentDTO dto = new CommentDTO();

        dto.setId(comment.getId());
        dto.setComment(comment.getComment());
        dto.setCreatedAt(comment.getCreatedAt());
        dto.setUpdatedAt(comment.getUpdatedAt());

        return dto;
    }

    public static Comment mapToEntity(CommentDTO dto) {
        Comment comment = new Comment();
        comment.setId(dto.getId());
        comment.setComment(dto.getComment());
        return comment;
    }
}
