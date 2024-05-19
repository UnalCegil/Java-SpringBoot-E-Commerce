package com.ecommerce.mapper;

import com.ecommerce.dto.CardDTO;
import com.ecommerce.entities.Card;
import com.ecommerce.entities.Customer;
import com.ecommerce.entities.Product;

public class CardMapper {
    public static CardDTO toCardDTO(Card card) {
        CardDTO dto = new CardDTO();
        dto.setId(card.getId());
        dto.setProductId(card.getProduct().getId());
        dto.setQuantity(card.getQuantity());
        dto.setCustomerId(card.getCustomer().getId());
        return dto;
    }

    public static Card toCard(CardDTO cardDTO) {
        Card card = new Card();
        card.setId(cardDTO.getId());
        card.setQuantity(cardDTO.getQuantity());
        
        Product product = new Product();
        product.setId(cardDTO.getProductId());
        card.setProduct(product);

        Customer customer = new Customer();
        customer.setId(cardDTO.getCustomerId());
        card.setCustomer(customer);
        
        return card;
    }
}
