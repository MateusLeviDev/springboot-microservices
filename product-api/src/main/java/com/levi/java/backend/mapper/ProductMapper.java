package com.levi.java.backend.mapper;

import com.levi.java.backend.domain.Category;
import com.levi.java.backend.domain.Product;
import com.levi.java.backend.mapper.requests.CategoryPostRequest;
import com.levi.java.backend.mapper.requests.ProductPostRequest;
import com.levi.java.backend.mapper.responses.CategoryResponse;
import com.levi.java.backend.mapper.responses.ProductResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public abstract class ProductMapper {
    public static final ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);

    public abstract Product toMapper(ProductPostRequest productPostRequest);
    public abstract Category toMapper(CategoryPostRequest categoryPostRequest);

    public abstract ProductResponse toMapperProductResponse(Product product);
    public abstract List<CategoryResponse> toMapperCategoryResponse(List<Category> category);

}
