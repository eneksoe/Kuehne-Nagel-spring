package com.example.Kuehne.Nagel.service;

import com.example.Kuehne.Nagel.dto.OrderDto;

import java.util.List;

public interface OrderService {
    void create(OrderDto customerOrder);

    List<OrderDto> getAll();

}
