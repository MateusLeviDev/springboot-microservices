package com.levi.java.backend.repository.impl;

import com.levi.java.backend.domain.Shop;
import com.levi.java.backend.mapper.responses.ShopReportResponse;
import com.levi.java.backend.repository.ReportRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDate;
import java.util.List;

public class ReportRepositoryImpl implements ReportRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Shop> getShopByFilters(LocalDate startDate, LocalDate endDate, Float minimumValue) {
        return null;
    }

    @Override
    public ShopReportResponse getReportByDate(LocalDate startDate, LocalDate endDate) {
        return null;
    }
}
