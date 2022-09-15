package com.example.Kuehne.Nagel.service;

import com.example.Kuehne.Nagel.dto.OrderDto;
import com.example.Kuehne.Nagel.models.Product;

import java.time.ZonedDateTime;
import java.util.List;

public interface OrderService {
    void create(OrderDto customerOrder);

    List<OrderDto> getAll();

    List<OrderDto> getAllByDate(ZonedDateTime date);

}
