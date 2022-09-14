package com.example.Kuehne.Nagel.service.impl;

import com.example.Kuehne.Nagel.dto.ProductDto;
import com.example.Kuehne.Nagel.mapper.Mapper;
import com.example.Kuehne.Nagel.models.Product;
import com.example.Kuehne.Nagel.repository.ProductRepository;
import com.example.Kuehne.Nagel.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository repository;

    @Autowired
    private Mapper<ProductDto, Product> mapper;

    @Override
    public List<ProductDto> getAll() {
        return mapper.toDtos(repository.findAll());
    }

    @Override
    public void create(ProductDto product) {
        final Product entity = mapper.toEntity(product);
        repository.save(entity);
    }

    @Override
    public ProductDto update(ProductDto product) {
        final Integer skuCode = product.getSkuCode();
        Product productFromDb = repository.findBySkuCode(skuCode);

        if (productFromDb == null) {
            String message = "Product with SkuCode = " + skuCode + " does not exist!";
            log.warn(message);
            throw new RuntimeException(message);
        }
        productFromDb.setUnitPrice(product.getUniPrice());

        return mapper.toDto(productFromDb);
    }
}
