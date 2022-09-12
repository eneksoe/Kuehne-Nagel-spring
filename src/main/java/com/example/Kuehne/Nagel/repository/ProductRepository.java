package com.example.Kuehne.Nagel.repository;

import com.example.Kuehne.Nagel.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
}
