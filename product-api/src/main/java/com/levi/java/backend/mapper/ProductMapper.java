package com.levi.java.backend.mapper;

import com.levi.java.backend.domain.Product;
import com.levi.java.backend.mapper.requests.ProductPostRequest;
import com.levi.java.backend.mapper.requests.ProductPutResponse;
import com.levi.java.backend.mapper.responses.ProductResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public abstract class ProductMapper {
    public static final ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);

    public abstract Product toMapper(Product product);

    public abstract Product toMapper(ProductResponseDto productResponseDto);

    public abstract Product toMapper(ProductPostRequest productPostRequest);

    public abstract Product toMapper(ProductPutResponse ProductPutResponse);

    @Mapping(source = "productIdentifier", target = "productIdentifier")
    @Mapping(source = "name", target = "name")
    @Mapping(source = "price", target = "price")
    @Mapping(source = "description", target = "description")
    @Mapping(source = "category", target = "category")
    public abstract ProductResponseDto toMapperProductResponse(Product product);

    @Mapping(source = "productIdentifier", target = "productIdentifier")
    @Mapping(source = "name", target = "name")
    @Mapping(source = "price", target = "price")
    @Mapping(source = "description", target = "description")
    @Mapping(source = "category", target = "category")
    public abstract List<ProductResponseDto> toMapperProductResponse(List<Product> product);
}
