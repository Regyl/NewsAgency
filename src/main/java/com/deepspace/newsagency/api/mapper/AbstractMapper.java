package com.deepspace.newsagency.api.mapper;


import com.deepspace.newsagency.api.controller.dto.response.AbstractDtoResponse;
import org.modelmapper.ModelMapper;

public abstract class AbstractMapper<T, S> {

    protected final ModelMapper mapper;

    AbstractMapper(ModelMapper mapper) {
        this.mapper = mapper;
    }

    public abstract T toEntity(S dto);

    public abstract AbstractDtoResponse toDto(T entity);
}
