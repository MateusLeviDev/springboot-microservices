package com.levi.java.backend.service;

import com.levi.java.backend.domain.Category;
import com.levi.java.backend.mapper.requests.CategoryPostRequest;
import com.levi.java.backend.mapper.responses.CategoryResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface CategoryService {
    Category save(CategoryPostRequest categoryPostRequest);
    Category findByIdOrThrowBadRequestException(Long id);
    Page<Category> findAllPageable(Pageable pageable);
}
