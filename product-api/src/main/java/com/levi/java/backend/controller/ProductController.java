package com.levi.java.backend.controller;

import com.levi.java.backend.mapper.requests.ProductPostRequest;
import com.levi.java.backend.mapper.requests.ProductPutRequest;
import com.levi.java.backend.service.impl.ProductServiceImpl;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductServiceImpl service;

    @PostMapping
    public ResponseEntity<?> createNewProduct(@RequestBody ProductPostRequest productPostRequest) {
        return new ResponseEntity<>(service.save(productPostRequest), HttpStatus.CREATED);
    }

    @GetMapping(path = "/all")
    public ResponseEntity<List<?>> getAllProducts() {
        return ResponseEntity.ok(service.findAllNonPageable());
    }

    @GetMapping(path = "/pageable")
    public ResponseEntity<Page<?>> getAllPageable(@PageableDefault(size = 15, sort = "id", direction = Sort.Direction.DESC) Pageable pageable) {
        return ResponseEntity.ok().body(service.findAllPageable(pageable));
    }

    @GetMapping(path = "/category/{categoryId}")
    public ResponseEntity<?> getProductByCategory(@PathVariable Long categoryId) {
        return ResponseEntity.ok(service.findProductByCategoryId(categoryId));
    }

    @GetMapping(path = "/{productIdentifier}")
    public ResponseEntity<?> getProductByIdentifier(@PathVariable String productIdentifier) {
        return ResponseEntity.ok(service.findProductByIdentifier(productIdentifier));
    }

    @DeleteMapping(path = "/{productId}")
    public ResponseEntity<?> deleteProduct(@PathVariable Long productId) {
        service.delete(productId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping
    public ResponseEntity<?> editProduct(@RequestBody ProductPutRequest productPutRequest) {
        this.service.replace(productPutRequest);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
