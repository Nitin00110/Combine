package com.combine.combine.service;

import com.combine.combine.DTO.OrderRequestDTO;
import com.combine.combine.DTO.OrderSummaryDTO;
import com.combine.combine.Entities.Order;
import com.combine.combine.repo.OrderRepo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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

    public OrderSummaryDTO createOrder(OrderRequestDTO orderRequestDTO) {
        Order newOrder = new Order(
                orderRequestDTO.CustomerName(),
                orderRequestDTO.ProductName(),
                orderRequestDTO.quantity(),
                orderRequestDTO.pricePerUnit(),
                Status.PLACED
        );
        Order savedOrder = orderRepo.save(newOrder);
        return OrderSummaryDTO.fromEntity(savedOrder);
    }

    public OrderSummaryDTO orderByID(Long id) {
        Order order =  orderRepo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Order not found"));

        return OrderSummaryDTO.fromEntity(order);
    }

    public Page<OrderSummaryDTO> allOrder(int pageNumber,int pageSize) {
        Pageable pageable =  PageRequest.of(pageNumber,pageSize);
        Page<Order> orderPage = orderRepo.findAll(pageable);
        return orderPage.map(OrderSummaryDTO::fromEntity);
    }

    public Order OrderShipped(Long id){
        Order order = orderRepo.findById(id).orElseThrow(() -> new IllegalArgumentException("Order not Found"));


        if(order.getStatus() == Status.SHIPPED || order.getStatus() == Status.DELIVERED){
            throw new IllegalArgumentException("Order has already been fulfilled");
        }
        order.setStatus(Status.SHIPPED);
        orderRepo.save(order);
        return order;
    }

    public Double orderRevenue(){
        return orderRepo.calculateTotalShippedRevenue();
    }

}
