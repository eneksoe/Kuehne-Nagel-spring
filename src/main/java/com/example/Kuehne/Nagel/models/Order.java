package com.example.Kuehne.Nagel.models;

import jakarta.persistence.*;
import lombok.*;

import javax.validation.constraints.Size;
import java.time.ZonedDateTime;

@Getter
@Setter
@RequiredArgsConstructor
@Builder

@Entity
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(max = 20)
    @Column(name = "order_number")
    private String orderNumber;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @Column(name = "submission_date")
    private ZonedDateTime submissionDate;
}
