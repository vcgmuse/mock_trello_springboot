package com.mock_trello.models;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

@Entity
@Table(name = "comments", uniqueConstraints = @UniqueConstraint(columnNames = "id"))
public class Comment {

	@Id
    @Column(name = "id", columnDefinition = "BINARY(16)")
    private byte[] id;

    @Column(length = 300)
    private String content;

    @ManyToOne
    @JoinColumn(name = "accountUser_id", nullable = false)
    private AccountUser accountUser;

    @ManyToOne
    @JoinColumn(name = "message_id", nullable = true)
    private Message message;

    @ManyToOne
    @JoinColumn(name = "card_id", nullable = true)
    private Card card;

    @ManyToOne
    @JoinColumn(name = "comment_id", nullable = true)
    private Comment parentComment;

    @OneToMany(mappedBy = "parentComment", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Comment> replies = new ArrayList<>();

	public byte[] getId() {
		return id;
	}

	public void setIdcomments(byte[] idcomments) {
		this.id = idcomments;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public AccountUser getAccountUser() {
		return accountUser;
	}

	public void setAccountUser(AccountUser accountUser) {
		this.accountUser = accountUser;
	}

	public Message getMessage() {
		return message;
	}

	public void setMessage(Message message) {
		this.message = message;
	}

	public Card getCard() {
		return card;
	}

	public void setCard(Card card) {
		this.card = card;
	}

	public Comment getParentComment() {
		return parentComment;
	}

	public void setParentComment(Comment parentComment) {
		this.parentComment = parentComment;
	}

	public List<Comment> getReplies() {
		return replies;
	}

	public void setReplies(List<Comment> replies) {
		this.replies = replies;
	}

	public void setId(byte[] id) {
		this.id = id;
	}

    // Getters and Setters
	
	
    
}
