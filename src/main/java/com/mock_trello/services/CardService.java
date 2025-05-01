package com.mock_trello.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mock_trello.models.Card;
import com.mock_trello.repositories.CardRepository;
import com.mock_trello.services.helpers.EntityUpdater;

@Service
public class CardService {

    private final CardRepository cardRepository;

    @Autowired
    public CardService(CardRepository cardRepository) {
        this.cardRepository = cardRepository;
    }

    // Create a new Card
    public Card saveCard(Card card) {
        // Perform any necessary validation here
        return cardRepository.save(card);
    }

    // Retrieve a Card by its ID
    public Optional<Card> findCardById(byte[] id) {
        return cardRepository.findById(id);
    }

    // Retrieve all Cards
    public List<Card> findAllCards() {
        return (List<Card>) cardRepository.findAll(); // Cast to List if CrudRepository is used
    }
    
    public Card updateCard(byte[] id, Card updatedCard) {
        return cardRepository.findById(id)
            .map(existingCard -> {
                // Use the helper function to update only non-null, different fields
                EntityUpdater.updateFields(existingCard, updatedCard);
                return cardRepository.save(existingCard);
            })
            .orElseThrow(() -> new RuntimeException("Card not found with ID: " + id));
    }

//    // Update an existing Card
//    public Card updateCard(byte[] id, Card updatedCard) {
//        return cardRepository.findById(id)
//            .map(card -> {
//                card.setName(updatedCard.getName());
//                card.setDescription(updatedCard.getDescription());
//                card.setAccountUser(updatedCard.getAccountUser());
//                card.setCardGroup(updatedCard.getCardGroup());
//                return cardRepository.save(card);
//            })
//            .orElseThrow(() -> new RuntimeException("Card not found with ID: " + id));
//    }

    // Delete a Card by ID
    public void deleteCard(byte[] id) {
        if (cardRepository.existsById(id)) {
            cardRepository.deleteById(id);
        } else {
            throw new RuntimeException("Card not found with ID: " + id);
        }
    }
}