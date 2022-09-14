package com.example.Kuehne.Nagel.service;

import com.example.Kuehne.Nagel.dto.OrderLineDto;

import java.util.List;

public interface OrderLineService {

    void create(OrderLineDto orderLine);

    List<OrderLineDto> getAll();
}
