package com.levi.java.backend.mapper;

import com.levi.java.backend.domain.Category;
import com.levi.java.backend.domain.Product;
import com.levi.java.backend.mapper.requests.CategoryPostRequest;
import com.levi.java.backend.mapper.requests.ProductPostRequest;
import com.levi.java.backend.mapper.responses.ProductResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public abstract class ProductMapper {
    public static final ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);

    @Mappings({
            @Mapping(target = "id", ignore = true),
            @Mapping(target = "name", source = "productPostRequest.name"),
            @Mapping(target = "price", source = "productPostRequest.price"),
            @Mapping(target = "description", source = "productPostRequest.description"),
            @Mapping(target = "productIdentifier", source = "productPostRequest.productIdentifier"),
            @Mapping(target = "category", source = "categoryPostRequest")
    })
    public abstract Product toProduct(ProductPostRequest productPostRequest, Category categoryPostRequest);

    public abstract List<ProductResponse> toProductResponseList(List<Product> products);

}
