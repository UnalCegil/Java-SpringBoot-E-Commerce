package com.ecommerce.services.order;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.dto.CardDTO;
import com.ecommerce.entities.Card;
import com.ecommerce.entities.Customer;
import com.ecommerce.entities.Order;
import com.ecommerce.entities.Product;
import com.ecommerce.mapper.CardMapper;
import com.ecommerce.repositories.CardRepository;
import com.ecommerce.repositories.OrderRepository;
import com.ecommerce.repositories.ProductRepository;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;
    
    @Autowired
    private CardRepository cardRepository;
    
    @Autowired
    private ProductRepository productRepository;

    @Override
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }
    
    @Override
    public List<Order> getAllOrdersForCustomer(Long customerId) {
        return orderRepository.findAllByCustomer_Id(customerId);
    }

    @Override
    public Order getOrderById(Long id) {
        return orderRepository.findById(id).orElse(null);
    }


    @Override
    public Order createOrder(List<CardDTO> cardDTOs) {
    	
        if (cardDTOs == null || cardDTOs.isEmpty()) {
            throw new IllegalArgumentException("Card listesi boş olamaz.");
        }

        List<Card> cardItems = cardDTOs.stream()
                                       .map(CardMapper::toCard)
                                       .collect(Collectors.toList());

        Order order = new Order();

        Customer customer = cardItems.get(0).getCustomer();
        order.setCustomer(customer);

        double totalPrice = 0.0;

        for (Card card : cardItems) {
            Product product = productRepository.findById(card.getProduct().getId())
                                                .orElseThrow(() -> new IllegalArgumentException("Ürün bulunamadı: " + card.getProduct().getId()));
            
            if (product.getStockQuantity() < card.getQuantity()) {
                throw new IllegalArgumentException("Yetersiz stok: " + product.getName());
            }
            
            product.setStockQuantity(product.getStockQuantity() - card.getQuantity());
            productRepository.save(product);
            
            totalPrice += product.getPrice() * card.getQuantity();
        }

        order.setTotalPrice(totalPrice);

        for (Card card : cardItems) {
            card.setOrder(order);
        }

        order.setCardItems(cardItems);

        
        Order savedOrder = orderRepository.save(order);
        for (Card card : cardItems) {
            card.setOrder(savedOrder);
            cardRepository.save(card);
        }

        return savedOrder;
    }

}


