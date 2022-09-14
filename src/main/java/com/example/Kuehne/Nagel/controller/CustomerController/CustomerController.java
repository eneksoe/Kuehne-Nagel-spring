package com.example.Kuehne.Nagel.controller.CustomerController;

import com.example.Kuehne.Nagel.dto.CustomerDto;
import com.example.Kuehne.Nagel.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @GetMapping("/all")
    public List<CustomerDto> getAll() {
        return customerService.getAll();
    }

    @PostMapping("/create")
    public ResponseEntity<CustomerDto> createCustomer(@RequestBody CustomerDto customer) {
        customerService.create(customer);
        return new ResponseEntity<>(customer, HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<CustomerDto> update(@RequestBody CustomerDto customer) {
        final CustomerDto updatedCustomer = customerService.update(customer);
        return new ResponseEntity<>(updatedCustomer, HttpStatus.OK);
    }
}
