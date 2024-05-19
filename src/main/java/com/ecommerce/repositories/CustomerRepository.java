package com.ecommerce.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecommerce.entities.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer,Long> {
    Customer findFirstByEmail(String email);
}
