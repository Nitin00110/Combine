package com.combine.combine.DTO;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record OrderRequestDTO(
        @NotBlank(message = "Customer Name is Required")
        String CustomerName,

        @NotBlank(message = "Product Name is Required")
        String ProductName,

        @Min(value = 1,message = "Quantity should be at least 1")
        int quantity,

        @NotNull(message = "Price cannot be <= 0")
        Double pricePerUnit) {
}
