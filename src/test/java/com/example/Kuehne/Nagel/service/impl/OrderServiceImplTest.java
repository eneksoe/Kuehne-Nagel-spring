package com.example.Kuehne.Nagel.service.impl;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.example.Kuehne.Nagel.dto.OrderDto;
import com.example.Kuehne.Nagel.mapper.Mapper;
import com.example.Kuehne.Nagel.models.Order;
import com.example.Kuehne.Nagel.repository.OrderRepository;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {OrderServiceImpl.class})
@ExtendWith(SpringExtension.class)
class OrderServiceImplTest {
    @MockBean
    private Mapper<OrderDto, Order> mapper;

    @MockBean
    private OrderRepository orderRepository;

    @Autowired
    private OrderServiceImpl orderServiceImpl;

    @Test
    void testCreate() {
        when(mapper.toEntity(any())).thenReturn(new Order());
        when(orderRepository.save(any())).thenReturn(new Order());
        OrderDto orderDto = mock(OrderDto.class);
        doNothing().when(orderDto).setSubmissionDate(any());
        orderServiceImpl.create(orderDto);
        verify(mapper).toEntity(any());
        verify(orderRepository).save(any());
        verify(orderDto).setSubmissionDate(any());
    }

    @Test
    void testGetAll() {
        ArrayList<OrderDto> orderDtoList = new ArrayList<>();
        when(mapper.toDtos(any())).thenReturn(orderDtoList);
        when(orderRepository.findAll()).thenReturn(new ArrayList<>());
        List<OrderDto> actualAll = orderServiceImpl.getAll();
        assertSame(orderDtoList, actualAll);
        assertTrue(actualAll.isEmpty());
        verify(mapper).toDtos(any());
        verify(orderRepository).findAll();
    }

    @Test
    void testGetAllByDate() {
        ArrayList<OrderDto> orderDtoList = new ArrayList<>();
        when(mapper.toDtos(any())).thenReturn(orderDtoList);
        when(orderRepository.findBySubmissionDate(any())).thenReturn(new ArrayList<>());
        List<OrderDto> actualAllByDate = orderServiceImpl.getAllByDate(null);
        assertSame(orderDtoList, actualAllByDate);
        assertTrue(actualAllByDate.isEmpty());
        verify(mapper).toDtos(any());
        verify(orderRepository).findBySubmissionDate(any());
    }
}

