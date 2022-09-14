package com.example.Kuehne.Nagel.models;

import jakarta.persistence.*;
import lombok.*;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor

@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(max = 50)
    @NotBlank
    private String name;

    @NotNull
    @Column(name = "sku_code")
    private Integer skuCode;

    @Min(value = 0)
    @Column(name = "unit_price")
    private Integer unitPrice;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "product")
    private List<OrderLine> orderLine;
}
