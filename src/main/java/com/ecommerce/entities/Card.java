package com.ecommerce.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(name = "cards")
@Data
@EqualsAndHashCode(callSuper=false)
public class Card extends BaseEntity {
    @ManyToOne
    private Product product;
    
    private int quantity;

    @ManyToOne
    private Customer customer;
    
    @ManyToOne
    private Order order;
}
