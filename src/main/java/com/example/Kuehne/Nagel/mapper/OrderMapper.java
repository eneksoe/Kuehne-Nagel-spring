package com.example.Kuehne.Nagel.mapper;

import com.example.Kuehne.Nagel.dto.OrderDto;
import com.example.Kuehne.Nagel.models.Order;
import com.example.Kuehne.Nagel.resolver.CustomerResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OrderMapper implements Mapper<OrderDto, Order> {

    @Autowired
    private CustomerResolver customerResolver;

    @Override
    public OrderDto toDto(Order entity) {
        if (entity == null) {
            return null;
        }

        return OrderDto.builder()
                .orderNumber(entity.getOrderNumber())
                .submissionDate(entity.getSubmissionDate())
                .customerEmail(entity.getCustomer().getEmail())
                .build();
    }

    @Override
    public Order toEntity(OrderDto dto) {
        var customer = customerResolver.resolveByEmail(dto.getCustomerEmail());
        if (customer == null) {
            return null;
        }

        return Order.builder()
                .orderNumber(dto.getOrderNumber())
                .submissionDate(dto.getSubmissionDate())
                .customer(customer)
                .build();
    }
}
