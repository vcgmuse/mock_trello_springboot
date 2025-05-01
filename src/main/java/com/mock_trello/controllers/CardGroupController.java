package com.mock_trello.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.mock_trello.models.CardGroup;
import com.mock_trello.services.CardGroupService;
import com.mock_trello.utils.UUIDConverter;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/cardgroups") // Base URL for this controller
public class CardGroupController {

    private final CardGroupService cardGroupService;

    @Autowired
    public CardGroupController(CardGroupService cardGroupService) {
        this.cardGroupService = cardGroupService;
    }

    // Create a new CardGroup
    @PostMapping
    public ResponseEntity<CardGroup> createCardGroup(@RequestBody CardGroup cardGroup) {
        CardGroup savedCardGroup = cardGroupService.saveCardGroup(cardGroup);
        return ResponseEntity.ok(savedCardGroup);
    }

    // Retrieve a CardGroup by ID
    @GetMapping("/{uuidString}")
    public ResponseEntity<CardGroup> getCardGroupById(@PathVariable String uuidString) {
    	byte[] id = UUIDConverter.fromString(uuidString);
        Optional<CardGroup> cardGroup = cardGroupService.findCardGroupById(id);
        return cardGroup.map(ResponseEntity::ok)
                        .orElse(ResponseEntity.notFound().build());
    }

    // Retrieve all CardGroups
    @GetMapping
    public ResponseEntity<List<CardGroup>> getAllCardGroups() {
        List<CardGroup> cardGroups = cardGroupService.findAllCardGroups();
        return ResponseEntity.ok(cardGroups);
    }

    // Update a CardGroup by ID
    @PutMapping("/{uuidString}")
    public ResponseEntity<CardGroup> updateCardGroup(
            @PathVariable String uuidString,
            @RequestBody CardGroup updatedCardGroup) {
    	byte[] id = UUIDConverter.fromString(uuidString);
        try {
            CardGroup cardGroup = cardGroupService.updateCardGroup(id, updatedCardGroup);
            return ResponseEntity.ok(cardGroup);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // Delete a CardGroup by ID
    @DeleteMapping("/{uuidString}")
    public ResponseEntity<Void> deleteCardGroup(@PathVariable String uuidString) {
    	byte[] id = UUIDConverter.fromString(uuidString);
        try {
            cardGroupService.deleteCardGroup(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}