package com.levi.java.backend.controller;

import com.levi.java.backend.domain.Category;
import com.levi.java.backend.mapper.requests.CategoryPostRequest;
import com.levi.java.backend.service.impl.CategoryServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/category")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryServiceImpl service;

    @PostMapping
    public ResponseEntity<Category> createCategory(@RequestBody CategoryPostRequest categoryPostRequest) {
        return new ResponseEntity<>(service.save(categoryPostRequest), HttpStatus.CREATED);
    }

    @GetMapping("/pageable")
    public ResponseEntity<Page<Category>> getAllPageable(@PageableDefault(sort = "id", direction = Sort.Direction.DESC)Pageable pageable) {
        return ResponseEntity.ok().body(service.findAllPageable(pageable));
    }
}
