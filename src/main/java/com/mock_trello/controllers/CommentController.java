package com.mock_trello.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.mock_trello.models.Comment;
import com.mock_trello.services.CommentService;
import com.mock_trello.utils.UUIDConverter;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/comments") // Base URL for this controller
public class CommentController {

    private final CommentService commentService;

    @Autowired
    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    // Create a new Comment
    @PostMapping
    public ResponseEntity<Comment> createComment(@RequestBody Comment comment) {
        Comment savedComment = commentService.saveComment(comment);
        return ResponseEntity.ok(savedComment);
    }

    // Retrieve a Comment by ID
    @GetMapping("/{uuidString}")
    public ResponseEntity<Comment> getCommentById(@PathVariable String uuidString) {
    	byte[] id = UUIDConverter.fromString(uuidString);
        Optional<Comment> comment = commentService.findCommentById(id);
        return comment.map(ResponseEntity::ok)
                      .orElse(ResponseEntity.notFound().build());
    }

    // Retrieve all Comments
    @GetMapping
    public ResponseEntity<List<Comment>> getAllComments() {
        List<Comment> comments = commentService.findAllComments();
        return ResponseEntity.ok(comments);
    }

    // Retrieve Replies for a Specific Comment
    @GetMapping("/{uuidString}/replies")
    public ResponseEntity<List<Comment>> getReplies(@PathVariable String uuidString) {
    	byte[] id = UUIDConverter.fromString(uuidString);
        List<Comment> replies = commentService.findReplies(id);
        return ResponseEntity.ok(replies);
    }

    // Update a Comment by ID
    @PutMapping("/{uuidString}")
    public ResponseEntity<Comment> updateComment(
            @PathVariable String uuidString, 
            @RequestBody Comment updatedComment) {
    	byte[] id = UUIDConverter.fromString(uuidString);
        try {
            Comment comment = commentService.updateComment(id, updatedComment);
            return ResponseEntity.ok(comment);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // Delete a Comment by ID
    @DeleteMapping("/{uuidString}")
    public ResponseEntity<Void> deleteComment(@PathVariable String uuidString) {
    	byte[] id = UUIDConverter.fromString(uuidString);
        try {
            commentService.deleteComment(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}