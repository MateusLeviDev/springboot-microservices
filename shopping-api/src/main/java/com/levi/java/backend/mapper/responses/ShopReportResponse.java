package com.levi.java.backend.mapper.responses;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ShopReportResponse {

    private Integer count;
    private Double total;
    private Double mean;

}
