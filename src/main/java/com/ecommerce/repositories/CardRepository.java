package com.ecommerce.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecommerce.entities.Card;

@Repository
public interface CardRepository extends JpaRepository<Card, Long> {
}
