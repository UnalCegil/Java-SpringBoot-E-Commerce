package com.ecommerce.dto;

import lombok.Data;

@Data
public class CustomerDTO {

    private Long id;

    private String name;

    private String email;
    
    private boolean isAdmin;

}
