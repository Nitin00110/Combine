package com.combine.combine.DTO;

import com.combine.combine.Entities.Order;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public record OrderSummaryDTO(
        Long id,
        String customerName,
        String productName,
        int quantity,
        Order.Status status) {
    public static OrderSummaryDTO fromEntity(Order order) {
        return new OrderSummaryDTO(
                order.getId(),
                order.getCustomerName(),
                order.getProductName(),
                order.getQuantity(),
                order.getStatus()
        );
    }
}