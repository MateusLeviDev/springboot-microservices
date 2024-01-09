package com.levi.java.backend.service;

import com.levi.java.backend.domain.Product;
import com.levi.java.backend.mapper.requests.ProductPostRequest;
import com.levi.java.backend.mapper.requests.ProductPutRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface ProductService {

    Optional<?> save(ProductPostRequest productPostRequest);

    List<?> findAllNonPageable();

    Page<?> findAllPageable(Pageable pageable);

    Product findByIdOrThrowBadRequestException(Long id);

    Optional<?> findProductByIdentifier(String productIdentifier);

    List<?> findProductByCategoryId(Long categoryId);

    void delete(Long id);

    void replace(ProductPutRequest productPutResponse);

}
