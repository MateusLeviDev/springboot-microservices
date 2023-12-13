package com.levi.java.backend.mapper.requests;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductPostRequest {
    @NotBlank
    private String name;
    @NotNull
    private Float price;
    @NotBlank
    private String description;
    @NotBlank
    private String productIdentifier;
    @NotNull
    private CategoryPostRequest category;
}
