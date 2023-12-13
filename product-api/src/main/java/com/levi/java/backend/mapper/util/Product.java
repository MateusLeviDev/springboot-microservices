package com.levi.java.backend.mapper.util;

import com.levi.java.backend.mapper.requests.CategoryPostRequest;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor(force = true)
public class Product {
    @NotBlank
    protected String name;
    @NotNull
    protected float price;
    @NotBlank
    protected String description;
    @NotBlank
    protected String productIdentifier;
    @NotBlank
    protected CategoryPostRequest category;
}
