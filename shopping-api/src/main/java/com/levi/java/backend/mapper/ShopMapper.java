package com.levi.java.backend.mapper;

import com.levi.java.backend.domain.Shop;
import com.levi.java.backend.mapper.requests.ShopPostRequest;
import com.levi.java.backend.mapper.responses.ShopResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public abstract class ShopMapper {

    public static final ShopMapper INSTANCE = Mappers.getMapper(ShopMapper.class);

    @Mappings({
            @Mapping(target = "id", ignore = true),
            @Mapping(target = "userIdentifier", source = "shopPostRequest.userIdentifier"),
            @Mapping(target = "total", source = "shopPostRequest.total"),
            @Mapping(target = "date", source = "shopPostRequest.date"),
            @Mapping(target = "items", source = "shopPostRequest.items")
    })
    public abstract Shop toShop(ShopPostRequest shopPostRequest);

    public abstract List<ShopResponse> toShopResponseList(List<Shop> shopList);
    public abstract ShopResponse toShopResponse(Shop shop);

}
