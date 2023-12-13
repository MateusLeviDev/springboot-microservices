package com.levi.java.backend.service;

import com.levi.java.backend.mapper.requests.ProductPostRequest;
import com.levi.java.backend.mapper.requests.ProductPutResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface ProductService {

    List<?> findAll();

    Optional<?> findByIdOrThrowBadRequestException(Long id);

    Page<?> findAllPageable(Pageable pageable);

    Optional<?> findProductByIdentifier(String productIdentifier);

    Optional<?> save(ProductPostRequest productPostRequest);

    void delete(Long id);

    void replace(ProductPutResponse productPutResponse);

}
