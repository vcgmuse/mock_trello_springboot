package com.mock_trello.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mock_trello.models.User;


public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}