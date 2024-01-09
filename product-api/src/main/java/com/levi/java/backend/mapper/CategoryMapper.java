package com.levi.java.backend.mapper;

import com.levi.java.backend.domain.Category;
import com.levi.java.backend.mapper.requests.CategoryPostRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public abstract class CategoryMapper {
    public static final CategoryMapper INSTANCE = Mappers.getMapper(CategoryMapper.class);

    @Mappings({
            @Mapping(target = "id", ignore = true),
            @Mapping(target = "name", source = "name")
    })
    public abstract Category toCategory(CategoryPostRequest categoryPostRequest);
}
