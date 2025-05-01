package com.mock_trello.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mock_trello.models.AccountUser;
import com.mock_trello.repositories.AccountUserRepository;

import java.util.List;
import java.util.Optional;

@Service
public class AccountUserService {
	@Autowired
    private final AccountUserRepository accountUserRepository;

    @Autowired
    public AccountUserService(AccountUserRepository accountUserRepository) {
        this.accountUserRepository = accountUserRepository;
    }

    // Create or Save a accountUser
    public AccountUser saveUser(AccountUser accountUser) {
        return accountUserRepository.save(accountUser);
    }
    
    public boolean userExists(String email) {
        return accountUserRepository.findByEmail(email).isPresent();
    }

    // Retrieve a accountUser by ID
    public Optional<AccountUser> findUserById(byte[] id) {
        return accountUserRepository.findById(id);
    }

    // Retrieve all Users
    public List<AccountUser> findAllUsers() {
        return (List<AccountUser>) accountUserRepository.findAll(); // Cast to List for CrudRepository
    }

    // Update an Existing accountUser
    public AccountUser updateUser(byte[] id, AccountUser updatedUser) {
        return accountUserRepository.findById(id)
            .map(accountUser -> {
                accountUser.setName(updatedUser.getName());
                return accountUserRepository.save(accountUser);
            })
            .orElseThrow(() -> new RuntimeException("accountUser not found with ID: " + id));
    }

    // Delete a accountUser by ID
    public void deleteUser(byte[] id) {
        if (accountUserRepository.existsById(id)) {
            accountUserRepository.deleteById(id);
        } else {
            throw new RuntimeException("accountUser not found with ID: " + id);
        }
    }
}