package com.combine.combine.controller;

import com.combine.combine.Entities.Order;
import com.combine.combine.service.OrderService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {
    private final OrderService orderService;

    private static final Logger log = LoggerFactory.getLogger(OrderController.class);
    public OrderController(OrderService orderService){
        this.orderService = orderService;
    }
    @PostMapping
    public ResponseEntity<Order> createOrder(@Valid @RequestBody Order order){
        log.info("Received request to create order for customer: {}", order.getCustomerName());
        Order created = orderService.createOrder(order);
        log.info("Order created successfully with ID: {}", created.getId());
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Order> getOrderById(@PathVariable Long id) {
        log.info("Fetching order with ID: {}", id);
        Order order = orderService.orderByID(id);
        return ResponseEntity.ok(order);
    }

    @GetMapping
    public ResponseEntity<List<Order>> getAllOrders() {
        log.info("Fetching all orders");
        return ResponseEntity.ok(orderService.allOrder());
    }

}
