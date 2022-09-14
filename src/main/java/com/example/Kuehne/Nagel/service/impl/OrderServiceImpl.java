package com.example.Kuehne.Nagel.service.impl;

import com.example.Kuehne.Nagel.dto.OrderDto;
import com.example.Kuehne.Nagel.mapper.Mapper;
import com.example.Kuehne.Nagel.models.Order;
import com.example.Kuehne.Nagel.repository.OrderRepository;
import com.example.Kuehne.Nagel.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.ZonedDateTime;
import java.util.List;

@Component
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository repository;

    @Autowired
    private Mapper<OrderDto, Order> mapper;

    @Override
    public void create(OrderDto order) {
        order.setSubmissionDate(ZonedDateTime.now());
        final var orderToCreate = mapper.toEntity(order);
        repository.save(orderToCreate);
    }

    @Override
    public List<OrderDto> getAll() {
        return mapper.toDtos(repository.findAll());
    }
}
