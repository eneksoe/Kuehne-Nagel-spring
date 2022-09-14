package com.example.Kuehne.Nagel.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class OrderLineDto {
    private Integer quantity;

    private Integer productSkuCode;

    private String order;
}
