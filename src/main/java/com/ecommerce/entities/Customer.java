package com.ecommerce.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(name = "customer")
@Data
@EqualsAndHashCode(callSuper=false)
public class Customer extends BaseEntity{
	
    private String name;

    private String email;
	
    private String password;
	
    private boolean isAdmin;

}
