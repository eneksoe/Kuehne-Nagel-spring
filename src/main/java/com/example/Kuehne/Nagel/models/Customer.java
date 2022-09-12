package com.example.Kuehne.Nagel.models;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigInteger;
import java.util.List;

@RequiredArgsConstructor
@Getter
@Setter
@Builder

@Entity
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(max = 20)
    @NotBlank
    @Column(name = "first_name")
    private String firstName;

    @Size(max = 20)
    @NotBlank
    @Column(name = "last_name")
    private String lastName;

    @NotNull
    @Column(name = "registration_code")
    private BigInteger registrationCode;

    @Size (max = 50)
    @NotBlank
    private String email;

    @Size (max = 20)
    @NotBlank
    @Column(name = "phone_number")
    private String phoneNumber;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    private List<Order> order;
}
