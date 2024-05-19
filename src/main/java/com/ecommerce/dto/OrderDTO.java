package com.ecommerce.dto;

import java.time.LocalDateTime;
import java.util.List;

import lombok.Data;

@Data
public class OrderDTO {
	private Long id;
    private List<CardDTO> cardItems;
    private LocalDateTime orderDate;
    private double totalPrice;
    private Long customerId;
}
