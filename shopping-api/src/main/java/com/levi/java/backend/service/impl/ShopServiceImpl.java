package com.levi.java.backend.service.impl;

import com.levi.java.backend.controller.exception.BusinessError;
import com.levi.java.backend.domain.Item;
import com.levi.java.backend.domain.Shop;
import com.levi.java.backend.mapper.ShopMapper;
import com.levi.java.backend.mapper.requests.ShopPostRequest;
import com.levi.java.backend.mapper.responses.ShopResponse;
import com.levi.java.backend.repository.ShopRepository;
import com.levi.java.backend.service.ShopService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ShopServiceImpl implements ShopService {

    private final ShopRepository repository;

    @Override
    public List<ShopResponse> getAllNonPageable() {
        return ShopMapper
                .INSTANCE
                .toShopResponseList(repository.findAll());
    }

    @Override
    public List<ShopResponse> getByUser(String userIdentifier) {
        List<Shop> allByUserIdentifier = repository.findAllByUserIdentifier(userIdentifier);

        return allByUserIdentifier.stream()
                .map(ShopMapper.INSTANCE::toShopResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<ShopResponse> getByDate(ShopPostRequest shopPostRequest) {
        List<Shop> allByDateGreaterThan = repository.findAllByDateGreaterThan(shopPostRequest.getDate());

        return allByDateGreaterThan.stream()
                .map(ShopMapper.INSTANCE::toShopResponse)
                .collect(Collectors.toList());
    }

    @Override
    public Shop findByIdOrThrowBadRequestException(Long productId) {
        return repository.findById(productId).orElseThrow(() -> new BusinessError("Not Found"));
    }

    @Override
    public Shop save(ShopPostRequest shopPostRequest) {
        shopPostRequest.setTotal(shopPostRequest.getItems()
                .stream()
                .map(Item::getPrice)
                .reduce((float) 0, Float::sum));

        Shop shop = ShopMapper.INSTANCE.toShop(shopPostRequest);
        shop.setDate(LocalDateTime.now());

        return repository.save(shop);
    }
}
