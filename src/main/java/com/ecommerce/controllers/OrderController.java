package com.ecommerce.controllers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.dto.CardDTO;
import com.ecommerce.dto.OrderDTO;
import com.ecommerce.entities.Order;
import com.ecommerce.mapper.OrderMapper;
import com.ecommerce.services.order.OrderService;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping("/allOrders/{id}")
    public List<OrderDTO> getAllOrdersForCustomer(@PathVariable Long id) {
    	
        return orderService.getAllOrdersForCustomer(id).stream()
                .map(OrderMapper::toOrderDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderDTO> getOrderById(@PathVariable Long id) {
        Order order = orderService.getOrderById(id);
        if (order != null) {
            return ResponseEntity.ok(OrderMapper.toOrderDTO(order));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<OrderDTO> createOrder(@RequestBody List<CardDTO> cardDTOs) {
        try {
            Order order = orderService.createOrder(cardDTOs);
            return ResponseEntity.ok(OrderMapper.toOrderDTO(order));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }
    
}

