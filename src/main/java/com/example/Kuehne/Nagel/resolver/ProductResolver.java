package com.example.Kuehne.Nagel.resolver;

import com.example.Kuehne.Nagel.exception.ResolvingException;
import com.example.Kuehne.Nagel.models.Product;
import com.example.Kuehne.Nagel.repository.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class ProductResolver {

    @Autowired
    private ProductRepository productRepository;

    public Product resolveBySkuCode(Integer skuCode) {
        final Product product = productRepository.findBySkuCode(skuCode);
        if (product == null) {
            String message = "Product whit sku code: " + skuCode + " dose not exist in the system!";
            log.warn(message);
            throw new ResolvingException(message);
        }
        return product;
    }
}
