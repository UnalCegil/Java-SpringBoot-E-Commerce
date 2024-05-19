package com.ecommerce.services.jwt;

import com.ecommerce.entities.Customer;
import com.ecommerce.repositories.CustomerRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class CustomerDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private CustomerRepository customerRepository;


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        Customer user = customerRepository.findFirstByEmail(email);
        if(user == null){
            throw new UsernameNotFoundException("User not found",null);
        }
        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), new ArrayList<>());
    }
}
