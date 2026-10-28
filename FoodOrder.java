
package com.example.model;

import java.math.BigDecimal;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class FoodOrder {

    private Long orderId;

    @NotBlank(message = "Customer name is required")
    @Size(
    		min = 2,
            max = 80,
            message = "Customer name must contain 2 to 80 characters"
            
    )
    private String customerName;

    @NotBlank(message = "Food item cannot be empty")
    private String foodItem;

    @NotNull(message = "Quantity is required")
    @Min(
    		value = 1, 
    		message = "Quantity must be at least 1")
    private Integer quantity;

    @NotNull(message = "Price is mandatory")
    @DecimalMin(
    		    value = "0.01",
                message = "Price must be greater than zero")
    private BigDecimal price;

    public FoodOrder() {
    }

    public FoodOrder(Long orderId, String customerName,
            String foodItem, Integer quantity, BigDecimal price) {
        this.orderId = orderId;
        this.customerName = customerName;
        this.foodItem = foodItem;
        this.quantity = quantity;
        this.price = price;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getFoodItem() {
        return foodItem;
    }

    public void setFoodItem(String foodItem) {
        this.foodItem = foodItem;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}