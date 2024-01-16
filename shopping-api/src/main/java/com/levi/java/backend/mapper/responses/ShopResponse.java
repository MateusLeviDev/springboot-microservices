package com.levi.java.backend.mapper.responses;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ShopResponse {

    @NotBlank
    private String userIdentifier;
    @NotNull
    private Float total;
    @NotNull
    private LocalDateTime date;
    @NotNull
    private List<ItemResponse> items;
}
