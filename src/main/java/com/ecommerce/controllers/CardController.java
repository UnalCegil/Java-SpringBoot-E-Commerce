package com.ecommerce.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.dto.CardDTO;
import com.ecommerce.entities.Card;
import com.ecommerce.services.card.CardService;

@RestController
@RequestMapping("/api/cards")
public class CardController {

    @Autowired
    private CardService cardService;

    @GetMapping
    public ResponseEntity<List<Card>> getAllCards() {
        List<Card> cards = cardService.getAllCards();
        return new ResponseEntity<>(cards, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Card> getCardById(@PathVariable Long id) {
        Card card = cardService.getCardById(id);
        if (card != null) {
            return new ResponseEntity<>(card, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<Card> createCard(@Valid @RequestBody CardDTO cardDTO) {
    	
        Card card = cardService.createCard(cardDTO);
        if (card != null) {
            return new ResponseEntity<>(card, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Card> updateCard(@PathVariable Long id, @Validated @RequestBody CardDTO cardDTO) {
        Card updatedCard = cardService.updateCard(id, cardDTO);
        if (updatedCard != null) {
            return new ResponseEntity<>(updatedCard, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCard(@PathVariable Long id) {
        boolean deleted = cardService.deleteCard(id);
        if (deleted) {
            return new ResponseEntity<>("Card deleted successfully", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Card not found or could not be deleted", HttpStatus.NOT_FOUND);
        }
    }
    
    

    
}
