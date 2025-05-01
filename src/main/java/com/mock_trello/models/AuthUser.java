package com.mock_trello.models;

import com.mock_trello.utils.UUIDConverter;

import jakarta.persistence.*;

@Entity
@Table(name = "auth_users")
public class AuthUser {

    @Id
    @Column(name = "id", columnDefinition = "BINARY(16)")
    private byte[] id;

    @OneToOne
    @JoinColumn(name = "account_user_id", nullable = false)
    private AccountUser accountUser;

    private String hashedPassword;
    private String refreshToken; // Optional: JWT refresh token
    private long lastLoginTimestamp;

    public AuthUser(AccountUser accountUser, String hashedPassword) {
        this.id = UUIDConverter.createByteId();
        this.accountUser = accountUser;
        this.hashedPassword = hashedPassword;
        this.lastLoginTimestamp = System.currentTimeMillis();
    }

	public byte[] getId() {
		return id;
	}

	public void setId(byte[] id) {
		this.id = id;
	}

	public AccountUser getAccountUser() {
		return accountUser;
	}

	public void setAccountUser(AccountUser accountUser) {
		this.accountUser = accountUser;
	}

	public String getHashedPassword() {
		return hashedPassword;
	}

	public void setHashedPassword(String hashedPassword) {
		this.hashedPassword = hashedPassword;
	}

	public String getRefreshToken() {
		return refreshToken;
	}

	public void setRefreshToken(String refreshToken) {
		this.refreshToken = refreshToken;
	}

	public long getLastLoginTimestamp() {
		return lastLoginTimestamp;
	}

	public void setLastLoginTimestamp(long lastLoginTimestamp) {
		this.lastLoginTimestamp = lastLoginTimestamp;
	}

    // Getters and Setters
    
}