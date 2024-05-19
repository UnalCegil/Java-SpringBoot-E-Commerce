package com.ecommerce.services.auth;

import com.ecommerce.dto.CustomerDTO;
import com.ecommerce.dto.SignupDTO;
import com.ecommerce.entities.Customer;

public interface AuthService {
    CustomerDTO createCustomer(SignupDTO signupDTO);
    Customer getCustomerById(Long id);

}
