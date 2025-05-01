package com.mock_trello.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.mock_trello.models.Card;
import com.mock_trello.services.CardService;
import com.mock_trello.utils.UUIDConverter;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/cards") // Base URL for this controller
public class CardController {

    private final CardService cardService;

    @Autowired
    public CardController(CardService cardService) {
        this.cardService = cardService;
    }

    // Create a new Card
    @PostMapping
    public ResponseEntity<Card> createCard(@RequestBody Card card) {
        Card savedCard = cardService.saveCard(card);
        return ResponseEntity.ok(savedCard);
    }

    // Retrieve a Card by ID
    @GetMapping("/{uuidString}")
    public ResponseEntity<Card> getCardById(@PathVariable String uuidString) {
    	byte[] id = UUIDConverter.fromString(uuidString);
        Optional<Card> card = cardService.findCardById(id);
        return card.map(ResponseEntity::ok)
                   .orElse(ResponseEntity.notFound().build());
    }

    // Retrieve all Cards
    @GetMapping
    public ResponseEntity<List<Card>> getAllCards() {
        List<Card> cards = cardService.findAllCards();
        return ResponseEntity.ok(cards);
    }

    // Update a Card by ID
    @PutMapping("/{uuidString}")
    public ResponseEntity<Card> updateCard(
            @PathVariable String uuidString,
            @RequestBody Card updatedCard) {
    	byte[] id = UUIDConverter.fromString(uuidString);
        try {
            Card card = cardService.updateCard(id, updatedCard);
            return ResponseEntity.ok(card);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // Delete a Card by ID
    @DeleteMapping("/{uuidString}")
    public ResponseEntity<Void> deleteCard(@PathVariable String uuidString) {
    	byte[] id = UUIDConverter.fromString(uuidString);
        try {
            cardService.deleteCard(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}