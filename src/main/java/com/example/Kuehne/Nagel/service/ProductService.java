package com.example.Kuehne.Nagel.service;

import com.example.Kuehne.Nagel.dto.ProductDto;

import java.util.List;

public interface ProductService {

    List<ProductDto> getAll();

    void create(ProductDto product);

    ProductDto update(ProductDto product);
}
