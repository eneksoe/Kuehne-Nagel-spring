package com.example.Kuehne.Nagel.service.impl;

import com.example.Kuehne.Nagel.dto.OrderLineDto;
import com.example.Kuehne.Nagel.mapper.Mapper;
import com.example.Kuehne.Nagel.models.OrderLine;
import com.example.Kuehne.Nagel.repository.OrderLineRepository;
import com.example.Kuehne.Nagel.service.OrderLineService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class OrderLineServiceImpl implements OrderLineService {

    @Autowired
    OrderLineRepository orderLineRepository;

    @Autowired
    private Mapper<OrderLineDto, OrderLine> mapper;

    @Override
    public List<OrderLineDto> getAll() {
        return mapper.toDtos(orderLineRepository.findAll());
    }

    @Override
    public void create(OrderLineDto orderLine) {
        final OrderLine entity = mapper.toEntity(orderLine);
        orderLineRepository.save(entity);
    }
}
