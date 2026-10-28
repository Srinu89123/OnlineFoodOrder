
package com.example.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.model.FoodOrder;
import com.example.exception.FoodOrderNotFoundException;

@Service
public class FoodOrderService {

    private final List<FoodOrder> orders = new ArrayList<>();
    private long nextId = 1;

    public List<FoodOrder> getAllOrders() {
        return new ArrayList<>(orders);
    }

    public FoodOrder getOrderById(Long id) {
        return orders.stream()
                .filter(order -> order.getOrderId().equals(id))
                .findFirst()
                .orElseThrow(() ->
                    new FoodOrderNotFoundException("Order not found with ID: " + id));
    }

    public FoodOrder addOrder(FoodOrder request) {
        FoodOrder order = new FoodOrder(
                nextId++,
                request.getCustomerName().trim(),
                request.getFoodItem().trim(),
                request.getQuantity(),
                request.getPrice()
        );

        orders.add(order);
        return order;
    }

    public FoodOrder updateOrder(Long id, FoodOrder request) {
        FoodOrder existing = getOrderById(id);

        existing.setCustomerName(request.getCustomerName().trim());
        existing.setFoodItem(request.getFoodItem().trim());
        existing.setQuantity(request.getQuantity());
        existing.setPrice(request.getPrice());

        return existing;
    }

    public void deleteOrder(Long id) {
        FoodOrder existing = getOrderById(id);
        orders.remove(existing);
    }

    public FoodOrder patchOrder(Long id, FoodOrder request) {
        FoodOrder existing = getOrderById(id);

        if (request.getCustomerName() != null) {
            if (request.getCustomerName().isBlank()) {
                throw new IllegalArgumentException("Customer name cannot be blank");
            }
            existing.setCustomerName(
                    request.getCustomerName().trim());
        }

        if (request.getFoodItem() != null) {
            if (request.getFoodItem().isBlank()) {
                throw new IllegalArgumentException("Food item cannot be empty");
            }
            existing.setFoodItem(request.getFoodItem().trim());
        }

        if (request.getQuantity() != null) {
            if (request.getQuantity() < 1) {
                throw new IllegalArgumentException("Quantity must be at least 1");
            }
            existing.setQuantity(request.getQuantity());
        }

        if (request.getPrice() != null) {
            if (request.getPrice().signum() <= 0) {
                throw new IllegalArgumentException("Price must be greater than zero");
            }
            existing.setPrice(request.getPrice());
        }

        return existing;
    }
}