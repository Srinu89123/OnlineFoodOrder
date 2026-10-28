
package com.example.controller;

import java.util.List;

import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.model.FoodOrder;
import com.example.service.FoodOrderService;

@RestController
@RequestMapping("/orders")
public class FoodOrderController {

    private final FoodOrderService service;

    public FoodOrderController(FoodOrderService service)
    {
        this.service = service;
    }

    // GET --> Get all orders
    
    @GetMapping
    public List<FoodOrder> getAllOrders()
    {
        return service.getAllOrders();
    }

    // GET--> Get order by ID
    
    @GetMapping("/{id}")
    public FoodOrder getOrderById(@PathVariable Long id)
    {
        return service.getOrderById(id);
    }

    // POST--> Adding a new order
    
    @PostMapping
    public ResponseEntity<FoodOrder> addOrder(
            @Valid @RequestBody FoodOrder order)
    {

        FoodOrder saved = service.addOrder(order);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(saved);
    }

    // PUT --> Update the complete order
    
    @PutMapping("/{id}")
    public FoodOrder updateOrder(
            @PathVariable Long id,
            @Valid @RequestBody FoodOrder order)
    {

        return service.updateOrder(id, order);
    }

    // DELETE--> Delete an order
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrder(
            @PathVariable Long id)
    {

        service.deleteOrder(id);
        return ResponseEntity.noContent().build();
    }

    // PATCH--> Update selected fields
    
    @PatchMapping("/{id}")
    public FoodOrder patchOrder(
            @PathVariable Long id,
            @RequestBody FoodOrder order) 
    {

        return service.patchOrder(id, order);
    }
}