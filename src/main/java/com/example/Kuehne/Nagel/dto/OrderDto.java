package com.example.Kuehne.Nagel.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class OrderDto {
    private String orderNumber;

    private CustomerDto customer;
}
