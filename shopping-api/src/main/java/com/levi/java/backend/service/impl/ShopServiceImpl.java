package com.levi.java.backend.service.impl;

import com.levi.java.backend.controller.exception.BusinessError;
import com.levi.java.backend.domain.Item;
import com.levi.java.backend.domain.Shop;
import com.levi.java.backend.mapper.ShopMapper;
import com.levi.java.backend.mapper.requests.ShopPostRequest;
import com.levi.java.backend.mapper.responses.ShopReportResponse;
import com.levi.java.backend.mapper.responses.ShopResponse;
import com.levi.java.backend.repository.ReportRepository;
import com.levi.java.backend.repository.ShopRepository;
import com.levi.java.backend.service.ShopService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ShopServiceImpl implements ShopService {

    private final ShopRepository shopRepository;
    private final ReportRepository reportRepository;

    @Autowired
    public ShopServiceImpl(@Qualifier("reportRepositoryImpl") ReportRepository reportRepository,
                           @Qualifier("shopRepository") ShopRepository shopRepository) {
        this.reportRepository = reportRepository;
        this.shopRepository = shopRepository;
    }

    @Override
    public List<ShopResponse> getAllNonPageable() {
        return ShopMapper
                .INSTANCE
                .toShopResponseList(shopRepository.findAll());
    }

    @Override
    public List<ShopResponse> getByUser(String userIdentifier) {
        List<Shop> allByUserIdentifier = shopRepository.findAllByUserIdentifier(userIdentifier);

        return allByUserIdentifier.stream()
                .map(ShopMapper.INSTANCE::toShopResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<ShopResponse> getByDate(ShopPostRequest shopPostRequest) {
        List<Shop> allByDateGreaterThan = shopRepository.findAllByDateGreaterThan(shopPostRequest.getDate());

        return allByDateGreaterThan.stream()
                .map(ShopMapper.INSTANCE::toShopResponse)
                .collect(Collectors.toList());
    }

    @Override
    public Shop findByIdOrThrowBadRequestException(Long productId) {
        return shopRepository.findById(productId).orElseThrow(() -> new BusinessError("Not Found"));
    }

    @Override
    public Shop save(ShopPostRequest shopPostRequest) {
        shopPostRequest.setTotal(shopPostRequest.getItems()
                .stream()
                .map(Item::getPrice)
                .reduce((float) 0, Float::sum));

        Shop shop = ShopMapper.INSTANCE.toShop(shopPostRequest);
        shop.setDate(LocalDateTime.now());

        return shopRepository.save(shop);
    }

    @Override
    public List<ShopResponse> getShopByFilters(LocalDate startDate, LocalDate endDate, Float minimumValue) {
        List<Shop> shopByFilters = reportRepository.getShopByFilters(startDate, endDate, minimumValue);
        return shopByFilters.stream()
                .map(ShopMapper.INSTANCE::toShopResponse)
                .collect(Collectors.toList());
    }

    @Override
    public ShopReportResponse getReportByDate(LocalDate startDate, LocalDate endDate) {
        return reportRepository.getReportByDate(startDate, endDate);
    }
}
