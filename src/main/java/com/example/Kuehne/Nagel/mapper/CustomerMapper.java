package com.example.Kuehne.Nagel.mapper;

import com.example.Kuehne.Nagel.dto.CustomerDto;
import com.example.Kuehne.Nagel.models.Customer;
import org.springframework.stereotype.Component;

@Component
public class CustomerMapper implements Mapper<CustomerDto, Customer> {
    @Override
    public CustomerDto toDto(Customer entity) {
        if (entity == null) {
            return null;
        }

        return CustomerDto.builder()
                .firstName(entity.getFirstName())
                .lastName(entity.getLastName())
                .registrationCode(entity.getRegistrationCode())
                .email(entity.getEmail())
                .phoneNumber(entity.getPhoneNumber())
                .build();
    }

    @Override
    public Customer toEntity(CustomerDto dto) {
        if (dto == null) {
            return null;
        }

        return Customer.builder()
                .firstName(dto.getFirstName())
                .lastName(dto.getLastName())
                .registrationCode(dto.getRegistrationCode())
                .email(dto.getEmail())
                .phoneNumber(dto.getPhoneNumber())
                .build();
    }
}
