package com.example.Kuehne.Nagel.controller;

import com.example.Kuehne.Nagel.dto.CustomerDto;
import com.example.Kuehne.Nagel.dto.OrderDto;
import com.example.Kuehne.Nagel.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.ZonedDateTime;
import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping("/all")
    public List<OrderDto> getAll() {
        return orderService.getAll();
    }

    @GetMapping("/all-by-date")
    public List<OrderDto> getAllByDate(@PathVariable ZonedDateTime date) {
        return orderService.getAllByDate(date);
    }

    @PostMapping("/create")
    public ResponseEntity<CustomerDto> create(@RequestBody OrderDto customerOrder) {
        orderService.create(customerOrder);
        return new ResponseEntity(HttpStatus.OK);
    }
}
