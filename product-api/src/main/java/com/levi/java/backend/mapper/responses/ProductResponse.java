package com.levi.java.backend.mapper.responses;

import com.levi.java.backend.mapper.requests.CategoryPostRequest;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductResponse {
    private Long id;
    private String name;
    private String description;
    private CategoryPostRequest category;
}
