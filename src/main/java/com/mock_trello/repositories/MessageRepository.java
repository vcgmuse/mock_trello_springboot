package com.mock_trello.repositories;

import org.springframework.data.repository.CrudRepository;

import com.mock_trello.models.Message;

public interface MessageRepository extends CrudRepository <Message, byte[]> {

}
