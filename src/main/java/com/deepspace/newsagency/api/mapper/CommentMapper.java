package com.deepspace.newsagency.api.mapper;

import com.deepspace.newsagency.annotation.Mapper;
import com.deepspace.newsagency.api.controller.dto.response.CommentDtoResponse;
import com.deepspace.newsagency.entity.Comment;
import org.modelmapper.ModelMapper;

@Mapper
public record CommentMapper(ModelMapper mapper) {

    public CommentDtoResponse toDto(Comment comment) {
        return mapper.map(comment, CommentDtoResponse.class);
    }
}
