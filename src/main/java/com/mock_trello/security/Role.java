package com.mock_trello.security;

import com.mock_trello.models.User;
import com.mock_trello.utils.UUIDConverter;

import jakarta.persistence.*;

@Entity
@Table(name = "roles")
public class Role {

    @Id
    @Column(name = "id", columnDefinition = "BINARY(16)")
    private byte[] id;

    @Column(unique = true, nullable = false)
    private String name; // Example: "ADMIN", "USER", "GUEST"
    
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user; // Replaces the previous User reference
    
    public Role() {
        this.id = UUIDConverter.createByteId(); // Generate a secure binary UUID
    }

    public byte[] getId() {
        return id;
    }

    public void setId(byte[] id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}