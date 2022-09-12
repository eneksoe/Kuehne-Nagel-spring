package com.example.Kuehne.Nagel.repository;

import com.example.Kuehne.Nagel.models.OrderLine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderLineRepository extends JpaRepository<OrderLine, Long> {
}
