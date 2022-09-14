package com.example.Kuehne.Nagel.service;

import com.example.Kuehne.Nagel.dto.CustomerDto;

import java.util.List;

public interface CustomerService {

    void create(CustomerDto customer);

    CustomerDto update(CustomerDto customer);

    List<CustomerDto> getAll();
}
