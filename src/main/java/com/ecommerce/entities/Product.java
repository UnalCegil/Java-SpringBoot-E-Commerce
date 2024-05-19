package com.ecommerce.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(name = "products")
@Data
@EqualsAndHashCode(callSuper=false)
public class Product extends BaseEntity{
	
	private String name;
	
	private String code;
	
    private double price;
	
    private int stockQuantity;
}
