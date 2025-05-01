package com.mock_trello.models;

import java.util.List;
import java.util.ArrayList;

import com.mock_trello.utils.UUIDConverter;
import jakarta.persistence.*;

@Entity
@Table(name = "account_users", uniqueConstraints = @UniqueConstraint(columnNames = "id"))
public class AccountUser {

    @Id
    @Column(name = "id", columnDefinition = "BINARY(16)")
    private byte[] id;

    private String name;
    private String email;

//    @Enumerated(EnumType.STRING)
//    private Role role;  // Optional: Defines user permissions

    @OneToMany(mappedBy = "accountUser", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Workspace> workspaces = new ArrayList<>();

    public AccountUser() {
        this.id = UUIDConverter.createByteId();
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

//	public Role getRole() {
//		return role;
//	}
//
//	public void setRole(Role role) {
//		this.role = role;
//	}

	public List<Workspace> getWorkspaces() {
		return workspaces;
	}

	public void setWorkspaces(List<Workspace> workspaces) {
		this.workspaces = workspaces;
	}

    // Getters and Setters
    
}