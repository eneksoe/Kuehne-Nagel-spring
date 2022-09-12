package com.example.Kuehne.Nagel.repository;

import com.example.Kuehne.Nagel.models.Order;
import jdk.jfr.Registered;
import org.springframework.data.jpa.repository.JpaRepository;

@Registered
public interface OrderRepository  extends JpaRepository<Order, Long> {
}
