package com.ecommerce.entities;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(name = "orders")
@Data
@EqualsAndHashCode(callSuper=false)
public class Order extends BaseEntity {
	
	@OneToMany(mappedBy = "order")
    private List<Card> cardItems;
        
    private double totalPrice;

    @ManyToOne
    private Customer customer;
    
}
