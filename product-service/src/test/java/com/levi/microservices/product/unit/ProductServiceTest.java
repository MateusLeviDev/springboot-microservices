package com.levi.microservices.product.unit;

import com.levi.microservices.product.domain.Product;
import com.levi.microservices.product.dto.ProductRequest;
import com.levi.microservices.product.dto.ProductResponse;
import com.levi.microservices.product.repository.ProductRepository;
import com.levi.microservices.product.service.ProductService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {

    @Mock
    ProductRepository productRepository;

    @InjectMocks
    ProductService productService;

    ProductRequest productRequest;

    Product product;

    @BeforeEach
    void init() {
        productRequest = new ProductRequest("Product1", "Description1", new BigDecimal("100.00"));
        product = new Product("1", "Product1", "Description1", new BigDecimal("100.00"));
    }

    @Test
    void shouldSaveProductSuccessfully() {
        when(productRepository.save(any(Product.class))).thenReturn(product);

        // Act
        Optional<ProductResponse> productResponse = productService.createProduct(productRequest);

        // Assert
        assertTrue(productResponse.isPresent());
        assertEquals("Product1", productResponse.get().name());
        verify(productRepository, times(1)).save(any(Product.class));
    }

    @Test
    void shouldGetAllProducts() {
        when(productRepository.findAll()).thenReturn(List.of(product));

        // Act
        List<ProductResponse> products = productService.getAllProducts();

        // Assert
        Assertions.assertFalse(products.isEmpty());
        assertEquals(1, products.size());
        assertEquals("Product1", products.get(0).name());
        verify(productRepository, times(1)).findAll();
    }

    @Test
    void shouldGetById() {
        when(productRepository.findById(anyString())).thenReturn(Optional.of(product));

        // Act
        Product foundProduct = productService.getById("1");

        // Assert
        Assertions.assertNotNull(foundProduct);
        assertEquals("Product1", foundProduct.getName());
        verify(productRepository, times(1)).findById(anyString());
    }

    @Test
    void shouldUpdateProduct() {
        Product updatedProduct = new Product("1", "UpdatedProduct", "UpdatedDescription", new BigDecimal("150.00"));

        when(productRepository.findById(anyString())).thenReturn(Optional.of(product));
        when(productRepository.save(any(Product.class))).thenReturn(updatedProduct);

        // Act
        productService.update("1", productRequest);

        // Assert
        verify(productRepository, times(1)).findById(anyString());
        verify(productRepository, times(1)).save(any(Product.class));
    }

    @Test
    void shouldDeleteProduct() {
        when(productRepository.findById(anyString())).thenReturn(Optional.of(product));

        // Act
        productService.delete("1");

        // Assert
        verify(productRepository, times(1)).findById(anyString());
        verify(productRepository, times(1)).delete(any(Product.class));
    }
}
