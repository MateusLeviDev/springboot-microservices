package com.levi.java.backend.mapper;

import com.levi.java.backend.domain.Category;
import com.levi.java.backend.mapper.requests.CategoryPostRequest;
import com.levi.java.backend.mapper.responses.CategoryResponseDto;
import org.mapstruct.Mapping;

public abstract class CategoryMapper {
    public abstract Category toMapper(CategoryPostRequest categoryPostRequest);

    public abstract Category toMapper(CategoryResponseDto categoryResponseDto);

    @Mapping(source = "name", target = "name")
    @Mapping(source = "id", target = "id")
    public abstract CategoryResponseDto toMapperCategoryResponse(Category category);
}