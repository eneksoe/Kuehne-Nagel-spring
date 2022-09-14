package com.example.Kuehne.Nagel.resolver;

import com.example.Kuehne.Nagel.exception.ResolvingException;
import com.example.Kuehne.Nagel.models.Order;
import com.example.Kuehne.Nagel.repository.OrderRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class OrderResolver {

    @Autowired
    private OrderRepository orderRepository;

    public Order resolveByOrderNumber(String orderNumber) {
        final Order order = orderRepository.findByOrderNumber(orderNumber);
        if (orderNumber == null) {
            String message = "Order number: " + order + " dose not exist in the system!";
            log.warn(message);
            throw new ResolvingException(message);
        }
        return order;
    }
}
