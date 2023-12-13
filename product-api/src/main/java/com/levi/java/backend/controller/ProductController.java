package com.levi.java.backend.controller;

import com.levi.java.backend.mapper.requests.ProductPostRequest;
import com.levi.java.backend.service.impl.ProductServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/product")
@RequiredArgsConstructor
public class ProductController {

    private ProductServiceImpl service;

    @PostMapping
    public ResponseEntity<?> createNewProduct(@RequestBody ProductPostRequest productPostRequest) {
        return new ResponseEntity<>(service.save(productPostRequest), HttpStatus.CREATED);
    }
}
