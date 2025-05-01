//package com.mock_trello.models;
//
//import java.util.Date;
//import java.util.List;
//
//import jakarta.persistence.Column;
//import jakarta.persistence.Entity;
//import jakarta.persistence.GeneratedValue;
//import jakarta.persistence.GenerationType;
//import jakarta.persistence.Id;
//import jakarta.persistence.JoinColumn;
//import jakarta.persistence.JoinTable;
//import jakarta.persistence.ManyToMany;
//import jakarta.persistence.Table;
//import jakarta.persistence.Temporal;
//import jakarta.persistence.TemporalType;
//
//@Entity // Marks this class as a JPA entity, representing a database table
//@Table(name = "boards") // Specifies the name of the database table
//public class Board {
//
//    @Id // Marks the 'id' field as the primary key
//    @GeneratedValue(strategy = GenerationType.IDENTITY) // Configures auto-generation of the primary key
//    private Long id;
//
//    @Column(nullable = false) // Specifies that the 'name' field cannot be null in the database
//    private String name;
//
//    // One-to-Many relationship with User (owner)
//    @Column(name = "owner_id", nullable = false)
//    private Long ownerId;
//
//    @Temporal(TemporalType.TIMESTAMP) // Specifies the type of mapping for java.util.Date
//    @Column(name = "created_at", nullable = false, updatable = false) //  cannot be updated after creation
//    private Date createdAt;
//
//    @Temporal(TemporalType.TIMESTAMP)
//    @Column(name = "updated_at") // Add updated_at field
//    private Date updatedAt;
//
//    // Many-to-Many relationship with User (collaborators)
//    @ManyToMany
//    @JoinTable(
//        name = "board_user", // Name of the join table
//        joinColumns = @JoinColumn(name = "board_id"), // Foreign key for Board in the join table
//        inverseJoinColumns = @JoinColumn(name = "user_id") // Foreign key for User in the join table
//    )
//    private List<User> users; // List of users collaborating on this board
//
//    // Constructors
//
//    public Board() {
//        // Default constructor required by JPA
//    }
//
//    public Board(String name, Long ownerId, Date createdAt) {
//        this.name = name;
//        this.ownerId = ownerId;
//        this.createdAt = createdAt;
//    }
//    public Board(Long id, String name, Long ownerId, Date createdAt, Date updatedAt) {
//        this.id = id;
//        this.name = name;
//        this.ownerId = ownerId;
//        this.createdAt = createdAt;
//        this.updatedAt = updatedAt;
//    }
//
//    // Getters and Setters
//
//    public Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public Long getOwnerId() {
//        return ownerId;
//    }
//
//    public void setOwnerId(Long ownerId) {
//        this.ownerId = ownerId;
//    }
//
//    public Date getCreatedAt() {
//        return createdAt;
//    }
//
//    public void setCreatedAt(Date createdAt) {
//        this.createdAt = createdAt;
//    }
//
//     public Date getUpdatedAt() {
//        return updatedAt;
//    }
//
//    public void setUpdatedAt1(Date updatedAt) {
//        this.updatedAt = updatedAt;
//    }
//
//    public List<User> getUsers() {
//        return users;
//    }
//
//    public void setUsers(List<User> users) {
//        this.users = users;
//    }
//     public void setUpdatedAt(Date updatedAt) {
//        this.updatedAt = updatedAt;
//    }
//}
//
