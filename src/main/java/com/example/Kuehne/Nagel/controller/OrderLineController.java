package com.example.Kuehne.Nagel.controller;

import com.example.Kuehne.Nagel.dto.OrderLineDto;
import com.example.Kuehne.Nagel.service.OrderLineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order-line")
public class OrderLineController {

    @Autowired
    private OrderLineService orderLinerService;

    @GetMapping("/all")
    public List<OrderLineDto> getAll() {
        return orderLinerService.getAll();
    }

    @PostMapping("/create")
    public ResponseEntity<OrderLineDto> createOrderLine(@RequestBody OrderLineDto orderLine) {
        orderLinerService.create(orderLine);
        return new ResponseEntity<>(orderLine, HttpStatus.OK);
    }
}
