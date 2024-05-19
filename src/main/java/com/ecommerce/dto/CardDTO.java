package com.ecommerce.dto;

import lombok.Data;

@Data
public class CardDTO {
	private Long id;
	
    private Long productId;
    
    private int quantity;
    
    private Long customerId;
}
