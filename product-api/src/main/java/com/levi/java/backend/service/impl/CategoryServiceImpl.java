package com.levi.java.backend.service.impl;

import com.levi.java.backend.controller.exceptions.BusinessError;
import com.levi.java.backend.domain.Category;
import com.levi.java.backend.mapper.ProductMapper;
import com.levi.java.backend.mapper.requests.CategoryPostRequest;
import com.levi.java.backend.repository.CategoryRepository;
import com.levi.java.backend.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository repository;

    @Override
    public Category save(CategoryPostRequest categoryPostRequest) {
        return repository.save(ProductMapper.INSTANCE.toMapper(categoryPostRequest));
    }

    @Override
    public Category findByIdOrThrowBadRequestException(Long categoryId) {
        return repository.findById(categoryId).orElseThrow(() -> new BusinessError("CATEGORY NOT FOUND!"));
    }

    @Override
    public Page<Category> findAllPageable(Pageable pageable) {
        return repository.findAll(pageable);
    }
}
