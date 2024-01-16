package com.levi.java.backend.service;

import com.levi.java.backend.domain.Shop;
import com.levi.java.backend.mapper.requests.ShopPostRequest;
import com.levi.java.backend.mapper.responses.ShopResponse;

import java.util.List;

public interface ShopService {

    List<ShopResponse> getAllNonPageable();

    List<ShopResponse> getByUser(String userIdentifier);

    List<ShopResponse> getByDate(ShopPostRequest shopPostRequest);

    Shop findByIdOrThrowBadRequestException(Long productId);

    Shop save(ShopPostRequest shopPostRequest);
}
