package com.mock_trello.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.mock_trello.models.Message;
import com.mock_trello.repositories.MessageRepository;

import java.util.List;
import java.util.Optional;

@Service
public class MessageService {

    private final MessageRepository messageRepository;

    @Autowired
    public MessageService(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    // Create a new Message
    public Message saveMessage(Message message) {
        return messageRepository.save(message);
    }

    // Retrieve a Message by ID
    public Optional<Message> findMessageById(byte[] id) {
        return messageRepository.findById(id);
    }

    // Retrieve all Messages
    public List<Message> findAllMessages() {
        return (List<Message>) messageRepository.findAll(); // Cast to List for CrudRepository
    }

    // Update an existing Message
    public Message updateMessage(byte[] id, Message updatedMessage) {
        return messageRepository.findById(id)
            .map(message -> {
                message.setContent(updatedMessage.getContent());
                message.setAccountUser(updatedMessage.getAccountUser());
                return messageRepository.save(message);
            })
            .orElseThrow(() -> new RuntimeException("Message not found with ID: " + id));
    }

    // Delete a Message by ID
    public void deleteMessage(byte[] id) {
        if (messageRepository.existsById(id)) {
            messageRepository.deleteById(id);
        } else {
            throw new RuntimeException("Message not found with ID: " + id);
        }
    }
}