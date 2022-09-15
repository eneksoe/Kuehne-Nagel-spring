package com.example.Kuehne.Nagel.repository;

import com.example.Kuehne.Nagel.models.Order;
import jdk.jfr.Registered;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.ZonedDateTime;
import java.util.List;

@Registered
public interface OrderRepository  extends JpaRepository<Order, Long> {
    Order findByOrderNumber(String orderNumber);

     List<Order> findBySubmissionDate(ZonedDateTime date);
}
