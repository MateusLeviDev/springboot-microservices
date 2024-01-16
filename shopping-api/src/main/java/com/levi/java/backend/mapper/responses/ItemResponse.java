package com.levi.java.backend.mapper.responses;

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
public class ItemResponse {

    @NotBlank
    private String productIdentifier;
    @NotNull
    private Float price;

}
