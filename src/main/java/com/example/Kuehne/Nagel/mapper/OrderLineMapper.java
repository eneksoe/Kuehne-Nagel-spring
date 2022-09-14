package com.example.Kuehne.Nagel.mapper;

import com.example.Kuehne.Nagel.dto.OrderLineDto;
import com.example.Kuehne.Nagel.models.OrderLine;
import com.example.Kuehne.Nagel.resolver.OrderResolver;
import com.example.Kuehne.Nagel.resolver.ProductResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OrderLineMapper implements Mapper<OrderLineDto, OrderLine>{

    @Autowired
    private OrderResolver orderResolver;

    @Autowired
    private ProductResolver productResolver;
    @Override
    public OrderLineDto toDto(OrderLine entity) {
        if(entity == null){
            return null;
        }

        return OrderLineDto.builder()
                .order(entity.getOrder().getOrderNumber())
                .quantity(entity.getQuantity())
                .productSkuCode(entity.getProduct().getSkuCode())
                .build();
    }

    @Override
    public OrderLine toEntity(OrderLineDto dto) {
        var order = orderResolver.resolveByOrderNumber(dto.getOrder());
        var product = productResolver.resolveBySkuCode(dto.getProductSkuCode());
        return OrderLine.builder()
                .order(order)
                .quantity(dto.getQuantity())
                .product(product)
                .build();
    }
}
