package com.mock_trello.repositories;

import org.springframework.data.repository.CrudRepository;

import com.mock_trello.models.CardGroup;

public interface CardGroupRepository extends CrudRepository<CardGroup, byte[]>{

}
