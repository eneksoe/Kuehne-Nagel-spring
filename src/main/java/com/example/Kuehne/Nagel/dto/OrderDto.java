package com.example.Kuehne.Nagel.dto;

import lombok.Builder;
import lombok.Data;

import java.time.ZonedDateTime;

@Data
@Builder
public class OrderDto {
    private String orderNumber;

    private String customerEmail;

    private ZonedDateTime submissionDate;
}
