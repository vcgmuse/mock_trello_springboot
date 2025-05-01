package com.mock_trello.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.mock_trello.models.CardGroup;
import com.mock_trello.repositories.CardGroupRepository;

import java.util.List;
import java.util.Optional;

@Service
public class CardGroupService {

    private final CardGroupRepository cardGroupRepository;

    @Autowired
    public CardGroupService(CardGroupRepository cardGroupRepository) {
        this.cardGroupRepository = cardGroupRepository;
    }

    // Create a new CardGroup
    public CardGroup saveCardGroup(CardGroup cardGroup) {
        return cardGroupRepository.save(cardGroup);
    }

    // Retrieve a CardGroup by its ID
    public Optional<CardGroup> findCardGroupById(byte[] id) {
        return cardGroupRepository.findById(id);
    }

    // Retrieve all CardGroups
    public List<CardGroup> findAllCardGroups() {
        return (List<CardGroup>) cardGroupRepository.findAll();
    }

    // Update an existing CardGroup
    public CardGroup updateCardGroup(byte[] id, CardGroup updatedCardGroup) {
        return cardGroupRepository.findById(id)
            .map(cardGroup -> {
                cardGroup.setName(updatedCardGroup.getName());
                cardGroup.setAccountUser(updatedCardGroup.getAccountUser());
                cardGroup.setWorkspace(updatedCardGroup.getWorkspace());
                return cardGroupRepository.save(cardGroup);
            })
            .orElseThrow(() -> new RuntimeException("CardGroup not found with ID: " + id));
    }

    // Delete a CardGroup by ID
    public void deleteCardGroup(byte[] id) {
        if (cardGroupRepository.existsById(id)) {
            cardGroupRepository.deleteById(id);
        } else {
            throw new RuntimeException("CardGroup not found with ID: " + id);
        }
    }
}
