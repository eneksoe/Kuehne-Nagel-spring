package com.example.Kuehne.Nagel.mapper;

import com.example.Kuehne.Nagel.dto.ProductDto;
import com.example.Kuehne.Nagel.models.Product;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper implements Mapper<ProductDto, Product>{
    @Override
    public ProductDto toDto(Product entity) {
        if(entity == null){
            return null;
        }

        return ProductDto.builder()
                .name(entity.getName())
                .skuCode(entity.getSkuCode())
                .uniPrice(entity.getUnitPrice())
                .build();
    }

    @Override
    public Product toEntity(ProductDto dto) {
        if(dto == null){
            return null;
        }

        return Product.builder()
                .name(dto.getName())
                .skuCode(dto.getSkuCode())
                .unitPrice(dto.getUniPrice())
                .build();
    }
}
