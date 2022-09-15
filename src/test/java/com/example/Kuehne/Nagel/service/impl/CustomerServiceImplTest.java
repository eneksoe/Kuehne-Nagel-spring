package com.example.Kuehne.Nagel.service.impl;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.example.Kuehne.Nagel.dto.CustomerDto;
import com.example.Kuehne.Nagel.mapper.Mapper;
import com.example.Kuehne.Nagel.models.Customer;
import com.example.Kuehne.Nagel.repository.CustomerRepository;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {CustomerServiceImpl.class})
@ExtendWith(SpringExtension.class)
class CustomerServiceImplTest {
    @MockBean
    private CustomerRepository customerRepository;

    @Autowired
    private CustomerServiceImpl customerServiceImpl;

    @MockBean
    private Mapper<CustomerDto, Customer> mapper;

    @Test
    void testCreate() {
        when(mapper.toEntity(any())).thenReturn(new Customer());
        when(customerRepository.save(any())).thenReturn(new Customer());
        customerServiceImpl.create(mock(CustomerDto.class));
        verify(mapper).toEntity(any());
        verify(customerRepository).save(any());
        assertTrue(customerServiceImpl.getAll().isEmpty());
    }

    @Test
    void testCreate2() {
        when(mapper.toEntity(any())).thenReturn(new Customer());
        when(customerRepository.save(any())).thenThrow(new RuntimeException("An error occurred"));
        assertThrows(RuntimeException.class, () -> customerServiceImpl.create(mock(CustomerDto.class)));
        verify(mapper).toEntity(any());
        verify(customerRepository).save(any());
    }

    @Test
    void testUpdate() {
        when(mapper.toDto(any())).thenReturn(mock(CustomerDto.class));
        when(customerRepository.findByEmail(any())).thenReturn(new Customer());
        CustomerDto customerDto = mock(CustomerDto.class);
        when(customerDto.getFirstName()).thenReturn("Jane");
        when(customerDto.getLastName()).thenReturn("Doe");
        when(customerDto.getEmail()).thenReturn("jane.doe@example.org");
        customerServiceImpl.update(customerDto);
        verify(mapper).toDto(any());
        verify(customerRepository).findByEmail(any());
        verify(customerDto).getEmail();
        verify(customerDto).getFirstName();
        verify(customerDto).getLastName();
    }

    @Test
    void testUpdate2() {
        when(mapper.toDto(any())).thenReturn(mock(CustomerDto.class));
        when(customerRepository.findByEmail(any())).thenReturn(new Customer());
        CustomerDto customerDto = mock(CustomerDto.class);
        when(customerDto.getFirstName()).thenThrow(new RuntimeException("An error occurred"));
        when(customerDto.getLastName()).thenThrow(new RuntimeException("An error occurred"));
        when(customerDto.getEmail()).thenReturn("jane.doe@example.org");
        assertThrows(RuntimeException.class, () -> customerServiceImpl.update(customerDto));
        verify(customerRepository).findByEmail(any());
        verify(customerDto).getEmail();
        verify(customerDto).getFirstName();
    }

    @Test
    void testUpdate3() {
        when(mapper.toDto(any())).thenReturn(mock(CustomerDto.class));
        when(customerRepository.findByEmail(any())).thenReturn(null);
        CustomerDto customerDto = mock(CustomerDto.class);
        when(customerDto.getFirstName()).thenReturn("Jane");
        when(customerDto.getLastName()).thenReturn("Doe");
        when(customerDto.getEmail()).thenReturn("jane.doe@example.org");
        assertThrows(RuntimeException.class, () -> customerServiceImpl.update(customerDto));
        verify(customerRepository).findByEmail(any());
        verify(customerDto).getEmail();
    }

    @Test
    void testUpdate4() {
        when(mapper.toDto(any())).thenReturn(mock(CustomerDto.class));
        Customer customer = mock(Customer.class);
        when(customer.getPhoneNumber()).thenThrow(new RuntimeException("An error occurred"));
        doThrow(new RuntimeException("An error occurred")).when(customer).setFirstName(any());
        doThrow(new RuntimeException("An error occurred")).when(customer).setLastName(any());
        doThrow(new RuntimeException("An error occurred")).when(customer).setPhoneNumber(any());
        when(customerRepository.findByEmail(any())).thenReturn(customer);
        CustomerDto customerDto = mock(CustomerDto.class);
        when(customerDto.getFirstName()).thenReturn("Jane");
        when(customerDto.getLastName()).thenReturn("Doe");
        when(customerDto.getEmail()).thenReturn("jane.doe@example.org");
        assertThrows(RuntimeException.class, () -> customerServiceImpl.update(customerDto));
        verify(customerRepository).findByEmail(any());
        verify(customer).setFirstName(any());
        verify(customerDto).getEmail();
        verify(customerDto).getFirstName();
    }

    @Test
    void testUpdate5() {
        when(mapper.toDto(any())).thenReturn(mock(CustomerDto.class));
        when(customerRepository.findByEmail(any())).thenReturn(mock(Customer.class));
        new RuntimeException("An error occurred");
        new RuntimeException("An error occurred");
        new RuntimeException("An error occurred");
        new RuntimeException("An error occurred");
        CustomerDto customerDto = mock(CustomerDto.class);
        when(customerDto.getFirstName()).thenReturn("Jane");
        when(customerDto.getLastName()).thenReturn("Doe");
        when(customerDto.getEmail()).thenReturn(null);
        assertThrows(RuntimeException.class, () -> customerServiceImpl.update(customerDto));
        verify(customerDto).getEmail();
    }

    @Test
    void testUpdate6() {
        when(mapper.toDto(any())).thenReturn(mock(CustomerDto.class));
        when(customerRepository.findByEmail(any())).thenReturn(mock(Customer.class));
        new RuntimeException("An error occurred");
        new RuntimeException("An error occurred");
        new RuntimeException("An error occurred");
        new RuntimeException("An error occurred");
        CustomerDto customerDto = mock(CustomerDto.class);
        when(customerDto.getFirstName()).thenReturn("Jane");
        when(customerDto.getLastName()).thenReturn("Doe");
        when(customerDto.getEmail()).thenReturn("");
        assertThrows(RuntimeException.class, () -> customerServiceImpl.update(customerDto));
        verify(customerDto).getEmail();
    }

    @Test
    void testGetAll() {
        ArrayList<CustomerDto> customerDtoList = new ArrayList<>();
        when(mapper.toDtos(any())).thenReturn(customerDtoList);
        when(customerRepository.findAll()).thenReturn(new ArrayList<>());
        List<CustomerDto> actualAll = customerServiceImpl.getAll();
        assertSame(customerDtoList, actualAll);
        assertTrue(actualAll.isEmpty());
        verify(mapper).toDtos(any());
        verify(customerRepository).findAll();
    }

    @Test
    void testGetAll2() {
        when(mapper.toDtos(any())).thenReturn(new ArrayList<>());
        when(customerRepository.findAll()).thenThrow(new RuntimeException("An error occurred"));
        assertThrows(RuntimeException.class, () -> customerServiceImpl.getAll());
        verify(customerRepository).findAll();
    }

    @Test
    void testGetAll3() {
        when(mapper.toDtos(any())).thenThrow(new RuntimeException("An error occurred"));
        when(customerRepository.findAll()).thenReturn(new ArrayList<>());
        assertThrows(RuntimeException.class, () -> customerServiceImpl.getAll());
        verify(mapper).toDtos(any());
        verify(customerRepository).findAll();
    }
}

