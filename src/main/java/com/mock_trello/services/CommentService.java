package com.mock_trello.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.mock_trello.models.Comment;
import com.mock_trello.repositories.CommentRepository;

import java.util.List;
import java.util.Optional;

@Service
public class CommentService {

    private final CommentRepository commentRepository;

    @Autowired
    public CommentService(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    // Create a new Comment
    public Comment saveComment(Comment comment) {
        return commentRepository.save(comment);
    }

    // Retrieve a Comment by ID
    public Optional<Comment> findCommentById(byte[] id) {
        return commentRepository.findById(id);
    }

    // Retrieve all Comments
    public List<Comment> findAllComments() {
        return (List<Comment>) commentRepository.findAll(); // Cast to List
    }

    // Retrieve replies for a specific Comment
    public List<Comment> findReplies(byte[] parentCommentId) {
        return commentRepository.findRepliesByParentCommentId(parentCommentId);
    }

    // Update a Comment
    public Comment updateComment(byte[] id, Comment updatedComment) {
        return commentRepository.findById(id)
            .map(comment -> {
                comment.setContent(updatedComment.getContent());
                comment.setAccountUser(updatedComment.getAccountUser());
                comment.setMessage(updatedComment.getMessage());
                comment.setCard(updatedComment.getCard());
                return commentRepository.save(comment);
            })
            .orElseThrow(() -> new RuntimeException("Comment not found with ID: " + id));
    }

    // Delete a Comment by ID
    public void deleteComment(byte[] id) {
        if (commentRepository.existsById(id)) {
            commentRepository.deleteById(id);
        } else {
            throw new RuntimeException("Comment not found with ID: " + id);
        }
    }
}
