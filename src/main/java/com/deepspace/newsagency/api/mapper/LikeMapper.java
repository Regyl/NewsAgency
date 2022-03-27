package com.deepspace.newsagency.api.mapper;

import com.deepspace.newsagency.annotation.Mapper;
import com.deepspace.newsagency.api.controller.dto.response.AbstractDtoResponse;
import com.deepspace.newsagency.api.controller.dto.response.LikeDtoResponse;
import com.deepspace.newsagency.entity.Like;
import org.modelmapper.ModelMapper;

@Mapper
public class LikeMapper {

    private final ModelMapper mapper;

    public LikeMapper(ModelMapper mapper) {
        this.mapper = mapper;
    }

    public LikeDtoResponse toDto(Like entity) {
        return mapper.map(entity, LikeDtoResponse.class);
    }
}
