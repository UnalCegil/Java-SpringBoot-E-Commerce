package com.ecommerce.services.card;

import com.ecommerce.dto.CardDTO;
import com.ecommerce.entities.Card;

import java.util.List;

public interface CardService {
    List<Card> getAllCards();
    Card getCardById(Long id);
    Card createCard(CardDTO cardDTO);
    Card updateCard(Long id, CardDTO cardDTO);
    boolean deleteCard(Long id);
}

