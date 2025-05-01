package com.mock_trello.models;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

@Entity
@Table(name = "cards", uniqueConstraints = @UniqueConstraint(columnNames = "id"))
public class Card {

    @Id
    @Column(name = "id", columnDefinition = "BINARY(16)")
    private byte[] id;

    private String title;

    private String description;
    
//    @ManyToOne
//    @JoinColumn(name = "card_id", nullable = false)
//    private Long board_id;

    @ManyToOne
    @JoinColumn(name = "account_user_id", nullable = false)
    private AccountUser accountUser; // Replaces the previous User reference
    
    
    @ManyToOne
    @JoinColumn(name = "cardgroup_id", nullable = false)
    private CardGroup cardGroup; // Correct naming for clarity

    @OneToMany(mappedBy = "card", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Comment> comments = new ArrayList<>();

    // Constructors
    public Card() {}

    public Card(byte[] id, String title, String description, AccountUser accountUser, CardGroup cardGroup) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.accountUser = accountUser;
        this.cardGroup = cardGroup;
    }

    // Getters and Setters
    public byte[] getId() {
        return id;
    }

    public void setId(byte[] id) {
        this.id = id;
    }
    

    public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
        return description;
    }
	
	
//    public Long getBoard_id() {
//		return board_id;
//	}
//
//	public void setBoard_id(Long board_id) {
//		this.board_id = board_id;
//	}

	public void setDescription(String description) {
        this.description = description;
    }

    public AccountUser getAccountUser() {
        return accountUser;
    }

    public void setAccountUser(AccountUser accountUser) {
        this.accountUser = accountUser;
    }

    public CardGroup getCardGroup() {
        return cardGroup;
    }

    public void setCardGroup(CardGroup cardGroup) {
        this.cardGroup = cardGroup;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }
}