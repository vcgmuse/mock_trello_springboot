package com.mock_trello.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.mock_trello.models.Message;
import com.mock_trello.services.MessageService;
import com.mock_trello.utils.UUIDConverter;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/messages") // Base URL for this controller
public class MessageController {

    private final MessageService messageService;

    @Autowired
    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }

    // Create a new Message
    @PostMapping
    public ResponseEntity<Message> createMessage(@RequestBody Message message) {
        Message savedMessage = messageService.saveMessage(message);
        return ResponseEntity.ok(savedMessage);
    }

    // Retrieve a Message by ID
    @GetMapping("/{uuidString}")
    public ResponseEntity<Message> getMessageById(@PathVariable String uuidString) {
    	byte[] id = UUIDConverter.fromString(uuidString);
        Optional<Message> message = messageService.findMessageById(id);
        return message.map(ResponseEntity::ok)
                      .orElse(ResponseEntity.notFound().build());
    }

    // Retrieve all Messages
    @GetMapping
    public ResponseEntity<List<Message>> getAllMessages() {
        List<Message> messages = messageService.findAllMessages();
        return ResponseEntity.ok(messages);
    }

    // Update a Message by ID
    @PutMapping("/{uuidString}")
    public ResponseEntity<Message> updateMessage(
            @PathVariable String uuidString,
            @RequestBody Message updatedMessage) {
    	byte[] id = UUIDConverter.fromString(uuidString);
        try {
            Message message = messageService.updateMessage(id, updatedMessage);
            return ResponseEntity.ok(message);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // Delete a Message by ID
    @DeleteMapping("/{uuidString}")
    public ResponseEntity<Void> deleteMessage(@PathVariable String uuidString) {
    	byte[] id = UUIDConverter.fromString(uuidString);
        try {
            messageService.deleteMessage(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}