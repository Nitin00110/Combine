package com.combine.combine.service;

import com.combine.combine.Entities.Order;
import com.combine.combine.repo.OrderRepo;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicLong;

import static com.combine.combine.Entities.Order.Status;

@Service
public class OrderService {

    OrderRepo orderRepo;
    OrderService(OrderRepo orderRepo){
        this.orderRepo = orderRepo;
    }

    public Order createOrder(Order order) {
        if(order.getPricePerUnit() <= 0 || order.getQuantity() <= 0){
            throw new IllegalArgumentException("Price and Quantity must be > 0");
        }
        order.setStatus(Order.Status.PLACED);
        // Postgres automatically generates the ID here
        return orderRepo.save(order);
    }

    public Order orderByID(Long id) {
        return orderRepo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Order not found"));
    }

    public List<Order> allOrder() {
        return orderRepo.findAll();
    }

}
