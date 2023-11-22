package com.levi.java.backend.mapper.requests;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductPostRequest {
    @NotBlank
    private String productIdentifier;
    @NotBlank
    private String name;
    @NotBlank
    private float price;
    @NotBlank
    private String description;
    @NotBlank
    private CategoryPostRequest category;
}
