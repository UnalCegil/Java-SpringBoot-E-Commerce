package com.ecommerce.services.order;

import java.util.List;

import com.ecommerce.dto.CardDTO;
import com.ecommerce.entities.Order;

public interface OrderService {
    List<Order> getAllOrders();
    List<Order> getAllOrdersForCustomer(Long customerId);
    Order getOrderById(Long id);
    Order createOrder( List<CardDTO> cardDTOs);
}
