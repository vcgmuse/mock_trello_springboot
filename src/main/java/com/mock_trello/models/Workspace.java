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
@Table(name = "workspace", uniqueConstraints = @UniqueConstraint(columnNames = "id"))
public class Workspace {

    @Id
    @Column(name = "id", columnDefinition = "BINARY(16)")
    private byte[] id;

    private String name;

    @ManyToOne
    @JoinColumn(name = "accountUser_id", nullable = false)
    private AccountUser accountUser;

    @OneToMany(mappedBy = "workspace", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CardGroup> lists = new ArrayList<>();

    // Constructors
    public Workspace() {}

    public Workspace(byte[] id, String name, AccountUser accountUser) {
        this.id = id;
        this.name = name;
        this.accountUser = accountUser;
    }

    // Getters and Setters
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

    public AccountUser getAccountUser() {
        return accountUser;
    }

    public void setAccountUser(AccountUser accountUser) {
        this.accountUser = accountUser;
    }

    public List<CardGroup> getLists() {
        return lists;
    }

    public void setLists(List<CardGroup> lists) {
        this.lists = lists;
    }
}