package com.levi.java.backend.mapper.requests;

import com.levi.java.backend.domain.Item;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ShopPostRequest {

    @NotBlank
    private String userIdentifier;
    @NotNull
    private float total;
    private LocalDateTime date;
    @NotNull
    private List<Item> items;

}
