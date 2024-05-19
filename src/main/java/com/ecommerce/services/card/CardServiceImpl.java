package com.ecommerce.services.card;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.dto.CardDTO;
import com.ecommerce.entities.Card;
import com.ecommerce.entities.Customer;
import com.ecommerce.entities.Product;
import com.ecommerce.mapper.CardMapper;
import com.ecommerce.repositories.CardRepository;
import com.ecommerce.repositories.CustomerRepository;
import com.ecommerce.repositories.ProductRepository;
import com.ecommerce.services.product.ProductService;

@Service
public class CardServiceImpl implements CardService {

    @Autowired
    private CardRepository cardRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CustomerRepository customerRepository;
    
    @Autowired
    private ProductService productService;

    @Override
    public List<Card> getAllCards() {
        return cardRepository.findAll();
    }

    @Override
    public Card getCardById(Long id) {
        return cardRepository.findById(id).orElse(null);
    }

    @Override
    public Card createCard(CardDTO cardDTO) {
        if (productService.checkStockAvailability(cardDTO.getProductId(), cardDTO.getQuantity())) {
            Card card = CardMapper.toCard(cardDTO);
            return cardRepository.save(card);
        } else {
        	throw new RuntimeException("Stok adedi yetersizdir");
        }
    
    }
    
    @Override
    public Card updateCard(Long id, CardDTO cardDTO) {
        Optional<Card> existingCardOpt = cardRepository.findById(id);
        if (existingCardOpt.isPresent()) {
            Card existingCard = existingCardOpt.get();
            
            Optional<Product> productOpt = productRepository.findById(cardDTO.getProductId());
            Optional<Customer> customerOpt = customerRepository.findById(cardDTO.getCustomerId());

            if (productOpt.isPresent() && customerOpt.isPresent()) {
                existingCard.setProduct(productOpt.get());
                existingCard.setCustomer(customerOpt.get());
                existingCard.setQuantity(cardDTO.getQuantity());
                return cardRepository.save(existingCard);
            }
        }
        
        return null;
    }

    @Override
    public boolean deleteCard(Long id) {
        if (cardRepository.existsById(id)) {
            cardRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }
}
