package com.example.Kuehne.Nagel.dto;

import com.example.Kuehne.Nagel.models.Product;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class OrderLineDto {
    private ProductDto product;

    private Integer quantity;

    private OrderDto order;
}
