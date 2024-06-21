package com.levi.microservices.product.service;

import jakarta.persistence.EntityNotFoundException;
import com.levi.microservices.product.domain.Product;
import com.levi.microservices.product.dto.ProductRequest;
import com.levi.microservices.product.dto.ProductResponse;
import com.levi.microservices.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductService {

    private final ProductRepository productRepository;

    public Optional<ProductResponse> createProduct(ProductRequest productRequest) {
        Product product = this.productRepository.save(
                Product.builder()
                        .name(productRequest.name())
                        .description(productRequest.description())
                        .price(productRequest.price())
                        .build()
        );

        log.info("Product created successfully");

        return Product.from(product);
    }

    public List<ProductResponse> getAllProducts() {
        return this.productRepository.findAll()
                .stream()
                .map(product -> new ProductResponse(product.getId(), product.getName(),
                        product.getDescription(), product.getPrice()))
                .toList();
    }

    public Product getById(String id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Unable to find Product with id " + id));
    }

    public void update(String id, ProductRequest productRequest) {
        Product product = getById(id);
        Product productUpdated = productRepository.save(product.updateModel(productRequest));
        Product.from(productUpdated);
    }


    public void delete(String id) {
        Product product = getById(id);
        productRepository.delete(product);
    }
}
