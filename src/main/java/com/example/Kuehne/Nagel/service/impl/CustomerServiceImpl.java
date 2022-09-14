package com.example.Kuehne.Nagel.service.impl;

import com.example.Kuehne.Nagel.dto.CustomerDto;
import com.example.Kuehne.Nagel.mapper.Mapper;
import com.example.Kuehne.Nagel.models.Customer;
import com.example.Kuehne.Nagel.repository.CustomerRepository;
import com.example.Kuehne.Nagel.service.CustomerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepository repository;

    @Autowired
    private Mapper<CustomerDto, Customer> mapper;

    @Override
    public void create(CustomerDto customer) {
        final Customer entity = mapper.toEntity(customer);
        repository.save(entity);
    }

    @Override
    public CustomerDto update(CustomerDto customer) {
        final String email = customer.getEmail();
        if(email == null || email.isEmpty()){
            throw new RuntimeException("Email must be not Null or Empty!");
        }
        Customer customerFromDb = repository.findByEmail(email);
        if (customerFromDb == null) {
            String message = "Customer with email = " + email + "does not exist!";
            log.warn(message);
            throw new RuntimeException(message);
        }
        customerFromDb.setFirstName(customer.getFirstName());
        customerFromDb.setLastName(customer.getLastName());
        customerFromDb.setPhoneNumber(customerFromDb.getPhoneNumber());
        return mapper.toDto(customerFromDb);
    }

    @Override
    public List<CustomerDto> getAll() {
        return mapper.toDtos(repository.findAll());
    }
}
