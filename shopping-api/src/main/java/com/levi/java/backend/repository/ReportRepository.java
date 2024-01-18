package com.levi.java.backend.repository;

import com.levi.java.backend.domain.Shop;
import com.levi.java.backend.mapper.responses.ShopReportResponse;

import java.time.LocalDate;
import java.util.List;

public interface ReportRepository {

    public List<Shop> getShopByFilters(
            LocalDate startDate,
            LocalDate endDate,
            Float minimumValue);

    public ShopReportResponse getReportByDate(
            LocalDate startDate,
            LocalDate endDate);

}
