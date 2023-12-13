package com.levi.java.backend.service.impl;

import com.levi.java.backend.controller.exceptions.BusinessError;
import com.levi.java.backend.domain.Product;
import com.levi.java.backend.mapper.ProductMapper;
import com.levi.java.backend.mapper.requests.ProductPostRequest;
import com.levi.java.backend.mapper.requests.ProductPutResponse;
import com.levi.java.backend.repository.ProductRepository;
import com.levi.java.backend.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private ProductRepository repository;

    @Override
    public Optional<Product> save(ProductPostRequest productPostRequest) {
        return Optional.of(repository.save(ProductMapper.INSTANCE.toMapper(productPostRequest)));
    }

    @Override
    public List<?> findAll() {
//        List<Product> products = repository.findAll();
//        return ProductMapper.INSTANCE.toMapperProductResponse(products);
        return null;
    }

    @Override
    public Optional<?> findByIdOrThrowBadRequestException(Long id) {
        return Optional.ofNullable(repository.findById(id).orElseThrow(() -> new BusinessError("Product not found")));
    }

    @Override
    public Page<?> findAllPageable(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    public Optional<?> findProductByIdentifier(String productIdentifier) {
        Product byProductIdentifier = repository.findByProductIdentifier(productIdentifier);
        return Optional.ofNullable(ProductMapper.INSTANCE.toMapperProductResponse(byProductIdentifier));
    }

    @Override
    public void delete(Long id) {
        Optional<Product> productById = repository.findById(id);
        productById.ifPresent(product -> repository.delete(product));
    }

    @Override
    public void replace(ProductPutResponse productPutResponse) {
        Optional<?> optionalSavedProduct = this.findByIdOrThrowBadRequestException(productPutResponse.getId());
        Product savedProduct = (Product) optionalSavedProduct.orElseThrow(() -> new RuntimeException("Product not found"));
        //Product product = ProductMapper.INSTANCE.toMapper(productPutResponse);

//        product.setId(savedProduct.getId());
//        product.setName(productPutResponse.getName() != null ? productPutResponse.getName() : savedProduct.getName());
//        product.setPrice(productPutResponse.getPrice() != null ? productPutResponse.getPrice() : savedProduct.getPrice());
//        repository.save(product);
    }
}
