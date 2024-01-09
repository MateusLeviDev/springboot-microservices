package com.levi.java.backend.mapper.requests;

import com.levi.java.backend.domain.Category;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductPutRequest {
    private long id;
    private String name;
    private Float price;
    private String description;
    private String productIdentifier;
    private Category category;
}
