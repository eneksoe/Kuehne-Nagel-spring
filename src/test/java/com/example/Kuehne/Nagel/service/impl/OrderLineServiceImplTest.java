package com.example.Kuehne.Nagel.service.impl;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.example.Kuehne.Nagel.dto.OrderLineDto;
import com.example.Kuehne.Nagel.mapper.Mapper;
import com.example.Kuehne.Nagel.models.OrderLine;
import com.example.Kuehne.Nagel.repository.OrderLineRepository;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {OrderLineServiceImpl.class})
@ExtendWith(SpringExtension.class)
class OrderLineServiceImplTest {
    @MockBean
    private Mapper<OrderLineDto, OrderLine> mapper;

    @MockBean
    private OrderLineRepository orderLineRepository;

    @Autowired
    private OrderLineServiceImpl orderLineServiceImpl;

    @Test
    void testGetAll() {
        ArrayList<OrderLineDto> orderLineDtoList = new ArrayList<>();
        when(mapper.toDtos(any())).thenReturn(orderLineDtoList);
        when(orderLineRepository.findAll()).thenReturn(new ArrayList<>());
        List<OrderLineDto> actualAll = orderLineServiceImpl.getAll();
        assertSame(orderLineDtoList, actualAll);
        assertTrue(actualAll.isEmpty());
        verify(mapper).toDtos(any());
        verify(orderLineRepository).findAll();
    }

    @Test
    void testCreate() {
        when(mapper.toEntity(any())).thenReturn(new OrderLine());
        when(orderLineRepository.save(any())).thenReturn(new OrderLine());
        orderLineServiceImpl.create(mock(OrderLineDto.class));
        verify(mapper).toEntity(any());
        verify(orderLineRepository).save(any());
        assertTrue(orderLineServiceImpl.getAll().isEmpty());
    }
}

