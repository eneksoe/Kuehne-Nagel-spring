package com.example.Kuehne.Nagel.service.impl;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.example.Kuehne.Nagel.dto.ProductDto;
import com.example.Kuehne.Nagel.mapper.Mapper;
import com.example.Kuehne.Nagel.models.Product;
import com.example.Kuehne.Nagel.repository.ProductRepository;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {ProductServiceImpl.class})
@ExtendWith(SpringExtension.class)
class ProductServiceImplTest {
    @MockBean
    private Mapper<ProductDto, Product> mapper;

    @MockBean
    private ProductRepository productRepository;

    @Autowired
    private ProductServiceImpl productServiceImpl;

    @Test
    void testGetAll() {
        ArrayList<ProductDto> productDtoList = new ArrayList<>();
        when(mapper.toDtos(any())).thenReturn(productDtoList);
        when(productRepository.findAll()).thenReturn(new ArrayList<>());
        List<ProductDto> actualAll = productServiceImpl.getAll();
        assertSame(productDtoList, actualAll);
        assertTrue(actualAll.isEmpty());
        verify(mapper).toDtos(any());
        verify(productRepository).findAll();
    }

    @Test
    void testGetAll2() {
        when(mapper.toDtos(any())).thenReturn(new ArrayList<>());
        when(productRepository.findAll()).thenThrow(new RuntimeException("An error occurred"));
        assertThrows(RuntimeException.class, () -> productServiceImpl.getAll());
        verify(productRepository).findAll();
    }

    @Test
    void testGetAll3() {
        when(mapper.toDtos(any())).thenThrow(new RuntimeException("An error occurred"));
        when(productRepository.findAll()).thenReturn(new ArrayList<>());
        assertThrows(RuntimeException.class, () -> productServiceImpl.getAll());
        verify(mapper).toDtos(any());
        verify(productRepository).findAll();
    }

    @Test
    void testCreate() {
        when(mapper.toEntity(any())).thenReturn(new Product());
        when(productRepository.save(any())).thenReturn(new Product());
        productServiceImpl.create(mock(ProductDto.class));
        verify(mapper).toEntity(any());
        verify(productRepository).save(any());
        assertTrue(productServiceImpl.getAll().isEmpty());
    }

    @Test
    void testCreate2() {
        when(mapper.toEntity(any())).thenReturn(new Product());
        when(productRepository.save(any())).thenThrow(new RuntimeException("An error occurred"));
        assertThrows(RuntimeException.class, () -> productServiceImpl.create(mock(ProductDto.class)));
        verify(mapper).toEntity(any());
        verify(productRepository).save(any());
    }

    @Test
    void testUpdate() {
        when(mapper.toDto(any())).thenReturn(mock(ProductDto.class));
        when(productRepository.findBySkuCode(any())).thenReturn(new Product());
        ProductDto productDto = mock(ProductDto.class);
        when(productDto.getSkuCode()).thenReturn(1);
        when(productDto.getUnitPrice()).thenReturn(1);
        productServiceImpl.update(productDto);
        verify(mapper).toDto(any());
        verify(productRepository).findBySkuCode(any());
        verify(productDto).getSkuCode();
        verify(productDto).getUnitPrice();
    }

    @Test
    void testUpdate2() {
        when(mapper.toDto(any())).thenReturn(mock(ProductDto.class));
        when(productRepository.findBySkuCode(any())).thenReturn(new Product());
        ProductDto productDto = mock(ProductDto.class);
        when(productDto.getSkuCode()).thenThrow(new RuntimeException("An error occurred"));
        when(productDto.getUnitPrice()).thenThrow(new RuntimeException("An error occurred"));
        assertThrows(RuntimeException.class, () -> productServiceImpl.update(productDto));
        verify(productDto).getSkuCode();
    }

    @Test
    void testUpdate3() {
        when(mapper.toDto(any())).thenReturn(mock(ProductDto.class));
        when(productRepository.findBySkuCode(any())).thenReturn(null);
        ProductDto productDto = mock(ProductDto.class);
        when(productDto.getSkuCode()).thenReturn(1);
        when(productDto.getUnitPrice()).thenReturn(1);
        assertThrows(RuntimeException.class, () -> productServiceImpl.update(productDto));
        verify(productRepository).findBySkuCode(any());
        verify(productDto).getSkuCode();
    }

    @Test
    void testUpdate4() {
        when(mapper.toDto(any())).thenReturn(mock(ProductDto.class));
        Product product = mock(Product.class);
        doThrow(new RuntimeException("An error occurred")).when(product).setUnitPrice((Integer) any());
        when(productRepository.findBySkuCode(any())).thenReturn(product);
        ProductDto productDto = mock(ProductDto.class);
        when(productDto.getSkuCode()).thenReturn(1);
        when(productDto.getUnitPrice()).thenReturn(1);
        assertThrows(RuntimeException.class, () -> productServiceImpl.update(productDto));
        verify(productRepository).findBySkuCode(any());
        verify(product).setUnitPrice(any());
        verify(productDto).getSkuCode();
        verify(productDto).getUnitPrice();
    }
}

