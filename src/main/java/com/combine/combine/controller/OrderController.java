package com.combine.combine.controller;

import com.combine.combine.DTO.OrderRequestDTO;
import com.combine.combine.DTO.OrderSummaryDTO;
import com.combine.combine.Entities.Order;
import com.combine.combine.service.OrderService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orders")
public class OrderController {
    private final OrderService orderService;

    private static final Logger log = LoggerFactory.getLogger(OrderController.class);

    public OrderController(OrderService orderService){
        this.orderService = orderService;
    }

    @PostMapping
    public ResponseEntity<OrderSummaryDTO> createOrder(@Valid @RequestBody OrderRequestDTO order){

        log.info("Received request to create order for customer: {}", order.CustomerName());
        OrderSummaryDTO created = orderService.createOrder(order);
        log.info("Order created successfully with ID: {}", created.id());
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderSummaryDTO> getOrderById(@PathVariable Long id) {
        log.info("Fetching order with ID: {}", id);

        return ResponseEntity.ok(orderService.orderByID(id));
    }

    @GetMapping
    public ResponseEntity<Page<OrderSummaryDTO>> getAllOrders(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10")  int size) {
        log.info("Fetching all orders");
        return ResponseEntity.ok(orderService.allOrder(page,size));
    }

    @PutMapping("/{id}/fullfill")
    public ResponseEntity<Order> fulfillOrder(@PathVariable Long id){
        Order order = orderService.OrderShipped(id);
        log.info("Order {} is Shipped", id);
        return ResponseEntity.status(HttpStatus.OK).body(order);
    }

    @GetMapping("/revenue")
    public ResponseEntity<Double> revenue(){
        Double total  = orderService.orderRevenue();
        log.info("Total Revenue of Shipped Order is {}",total);
        return ResponseEntity.status(HttpStatus.OK).body(total);
    }
}
