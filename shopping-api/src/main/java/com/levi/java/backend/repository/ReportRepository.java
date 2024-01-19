package com.levi.java.backend.repository;

import com.levi.java.backend.domain.Shop;
import com.levi.java.backend.mapper.responses.ShopReportResponse;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ReportRepository {

    public List<Shop> getShopByFilters(
            LocalDate startDate,
            LocalDate endDate,
            Float minimumValue);

    public ShopReportResponse getReportByDate(
            LocalDate startDate,
            LocalDate endDate);

}
