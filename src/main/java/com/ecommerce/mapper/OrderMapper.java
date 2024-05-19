package com.ecommerce.mapper;

import java.util.stream.Collectors;

import com.ecommerce.dto.OrderDTO;
import com.ecommerce.entities.Customer;
import com.ecommerce.entities.Order;

public class OrderMapper {
    public static OrderDTO toOrderDTO(Order order) {
        OrderDTO dto = new OrderDTO();
        dto.setId(order.getId());
        dto.setTotalPrice(order.getTotalPrice());
        dto.setCustomerId(order.getCustomer().getId());
        dto.setCardItems(order.getCardItems().stream()
            .map(CardMapper::toCardDTO)
            .collect(Collectors.toList()));
        return dto;
    }

    public static Order toOrder(OrderDTO orderDTO) {
        Order order = new Order();
        order.setId(orderDTO.getId());
        Customer customer = new Customer();
        customer.setId(orderDTO.getCustomerId());
        order.setCustomer(customer);
        return order;
    }
}

