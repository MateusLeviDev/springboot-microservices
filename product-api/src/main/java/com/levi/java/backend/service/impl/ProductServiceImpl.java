package com.levi.java.backend.service.impl;

import com.levi.java.backend.controller.exceptions.BusinessError;
import com.levi.java.backend.domain.Category;
import com.levi.java.backend.domain.Product;
import com.levi.java.backend.mapper.ProductMapper;
import com.levi.java.backend.mapper.requests.ProductPostRequest;
import com.levi.java.backend.mapper.requests.ProductPutRequest;
import com.levi.java.backend.repository.CategoryRepository;
import com.levi.java.backend.repository.ProductRepository;
import com.levi.java.backend.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    @Override
    public Optional<Product> save(ProductPostRequest productPostRequest) {
        Category categoryById = categoryRepository.findById(productPostRequest.getCategory().getId())
                .orElseThrow(() -> new BusinessError("Category not found"));
        Product product = ProductMapper.INSTANCE.toProduct(productPostRequest, categoryById);

        return Optional.of(productRepository.save(product));
    }

    @Override
    public List<?> findAllNonPageable() {
        return ProductMapper.INSTANCE.toProductResponseList(productRepository.findAll());
    }

    @Override
    public Product findByIdOrThrowBadRequestException(Long id) {
        return productRepository.findById(id).orElseThrow(() -> new BusinessError("Product not found"));
    }

    @Override
    public Page<?> findAllPageable(Pageable pageable) {
        return productRepository.findAll(pageable);
    }

    @Override
    public Optional<?> findProductByIdentifier(String productIdentifier) {
        return Optional.ofNullable(this.productRepository.findByProductIdentifier(productIdentifier))
                .map(ProductMapper.INSTANCE::toProductResponse);
    }

    @Override
    public List<?> findProductByCategoryId(Long categoryId) {
        List<Product> products = this.productRepository.getProductByCategory(categoryId);

        return products.stream()
                .map(product -> ProductMapper.INSTANCE.toProductResponseList(products))
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Long id) {
        Optional<Product> productById = productRepository.findById(id);
        productById.ifPresent(productRepository::delete);
    }

    @Override
    public void replace(ProductPutRequest productPutRequest) {
        Product savedProduct = this.findByIdOrThrowBadRequestException(productPutRequest.getId());
        Product product = ProductMapper.INSTANCE.toProduct(productPutRequest);

        product.setId(savedProduct.getId());
        product.setName(productPutRequest.getName().isBlank() ? savedProduct.getName() : productPutRequest.getName());
        product.setPrice(productPutRequest.getPrice() != null ? productPutRequest.getPrice() : savedProduct.getPrice());
        product.setDescription(productPutRequest.getDescription().isBlank() ? savedProduct.getDescription() : productPutRequest.getDescription());
        product.setProductIdentifier(productPutRequest.getProductIdentifier().isBlank() ? savedProduct.getProductIdentifier() : productPutRequest.getProductIdentifier());
        product.setCategory(productPutRequest.getCategory() != null ? productPutRequest.getCategory() : savedProduct.getCategory());

        productRepository.save(product);
    }
}
