package com.levi.java.backend.service;

import com.levi.java.backend.domain.Shop;
import com.levi.java.backend.mapper.requests.ShopPostRequest;
import com.levi.java.backend.mapper.responses.ShopResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ShopService {

    List<ShopResponse> getAllNonPageable();

    List<ShopResponse> getByUser(String userIdentifier);

    List<ShopResponse> getByDate(ShopPostRequest shopPostRequest);

    Shop findByIdOrThrowBadRequestException(Long productId);

    Shop save(ShopPostRequest shopPostRequest);
}
