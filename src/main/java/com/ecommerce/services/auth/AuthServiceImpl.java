package com.ecommerce.services.auth;

import com.ecommerce.dto.CustomerDTO;
import com.ecommerce.dto.SignupDTO;
import com.ecommerce.entities.Customer;
import com.ecommerce.entities.Product;
import com.ecommerce.repositories.CustomerRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public CustomerDTO createCustomer(SignupDTO signupDTO) {
        Customer user = new Customer();
        user.setName(signupDTO.getName());
        user.setEmail(signupDTO.getEmail());
        user.setPassword(new BCryptPasswordEncoder().encode(signupDTO.getPassword()));
        user.setAdmin(signupDTO.isAdmin());
        Customer createdUser = customerRepository.save(user);
        CustomerDTO userDTO = new CustomerDTO();
        userDTO.setId(createdUser.getId());
        userDTO.setEmail(createdUser.getEmail());
        userDTO.setName(createdUser.getName());
        userDTO.setAdmin(createdUser.isAdmin());
        return userDTO;
    }
    
    @Override
    public Customer getCustomerById(Long id) {
        return customerRepository.findById(id).orElse(null);
    }

}
