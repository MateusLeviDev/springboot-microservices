package com.levi.java.backend.repository.impl;

import com.levi.java.backend.mapper.responses.ShopReportResponse;
import com.levi.java.backend.repository.ReportRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.math.BigInteger;
import java.time.LocalDate;
import java.util.List;

@Repository
public class ReportRepositoryImpl implements ReportRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List getShopByFilters(LocalDate startDate, LocalDate endDate, Float minimumValue) {
        String sqlQuery = buildSqlQuery(startDate, endDate, minimumValue);
        Query query = createQueryWithParameters(sqlQuery, startDate, endDate, minimumValue);
        return query.getResultList();
    }

    @Override
    public ShopReportResponse getReportByDate(LocalDate startDate, LocalDate endDate) {
        String sqlQuery = buildReportSqlQuery(startDate, endDate);
        Query query = createReportQueryWithParameters(sqlQuery, startDate, endDate);
        Object[] result = (Object[]) query.getSingleResult();
        return createShopReportDTO(result);
    }

    private String buildReportSqlQuery(LocalDate startDate, LocalDate endDate) {
        StringBuilder sb = new StringBuilder();
        sb.append("select count(sp.id), sum(sp.total), avg(sp.total) ");
        sb.append("from shopping.shop sp ");
        sb.append("where sp.date >= :startDate ");
        sb.append("and sp.date <= :endDate ");
        return sb.toString();
    }

    private Query createReportQueryWithParameters(String sqlQuery, LocalDate startDate, LocalDate endDate) {
        Query query = entityManager.createNativeQuery(sqlQuery);
        query.setParameter("startDate", startDate.atTime(0, 0));
        query.setParameter("endDate", endDate.atTime(23, 59));
        return query;
    }

    private ShopReportResponse createShopReportDTO(Object[] result) {
        ShopReportResponse shopReportResponse = new ShopReportResponse();
        shopReportResponse.setCount(((BigInteger) result[0]).intValue());
        shopReportResponse.setTotal((Double) result[1]);
        shopReportResponse.setMean((Double) result[2]);
        return shopReportResponse;
    }

    private String buildSqlQuery(LocalDate startDate, LocalDate endDate, Float minimumValue) {
        StringBuilder sb = new StringBuilder();

        sb.append("select s ");
        sb.append("from shop s ");
        sb.append("where s.date >= :startDate ");

        if (endDate != null) {
            sb.append("and s.date <= :endDate ");
        }
        if (minimumValue != null) {
            sb.append("and s.total <= :minimumValue ");
        }
        return sb.toString();
    }

    private Query createQueryWithParameters(String sqlQuery, LocalDate startDate, LocalDate endDate, Float minimumValue) {
        Query query = entityManager.createQuery(sqlQuery);
        query.setParameter("startDate", startDate.atTime(0, 0));
        if (endDate != null) {
            query.setParameter("endDate", endDate.atTime(23, 59));
        }
        if (minimumValue != null) {
            query.setParameter("minimumValue", minimumValue);
        }
        return query;
    }
}
