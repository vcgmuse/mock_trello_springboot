package com.mock_trello.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.mock_trello.models.Card;

@Repository
public interface CardRepository extends CrudRepository<Card, byte[]> {
	
    // Custom query methods can be added here if needed, e.g., findByName()
}
