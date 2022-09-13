package com.example.Kuehne.Nagel.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProductDto {
    private String name;

    private Integer skuCode;

    private Integer uniPrice;
}
