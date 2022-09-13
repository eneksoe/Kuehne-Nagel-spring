package com.example.Kuehne.Nagel.dto;

import lombok.Builder;
import lombok.Data;

import java.math.BigInteger;

@Data
@Builder
public class CustomerDto {
    private String firstName;

    private String lastName;

    private BigInteger registrationCode;

    private String email;

    private String phoneNumber;
}
