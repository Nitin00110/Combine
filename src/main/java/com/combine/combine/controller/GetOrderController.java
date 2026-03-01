package com.combine.combine.controller;

import com.combine.combine.Entities.Order;
import com.combine.combine.service.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GetOrderController {
    private final OrderService orderService;

    public GetOrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/orders/{id}")
    public ResponseEntity<?> getOrderId(@PathVariable long id){
        try{
            Order order =  orderService.orderByID(id);
            return ResponseEntity.status(HttpStatus.OK).body(order);
        }
        catch (IllegalArgumentException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/orders")
    public ResponseEntity<?> getAllOrder(){
        return ResponseEntity.ok(orderService.allOrder());
    }
}
