package com.levi.java.backend.mapper.requests;

import com.levi.java.backend.domain.Item;
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
public class ItemPostRequest {

    @NotBlank
    private String productIdentifier;
    @NotNull
    private Float price;

}
