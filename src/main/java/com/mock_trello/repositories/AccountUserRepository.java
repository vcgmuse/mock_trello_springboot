package com.mock_trello.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.mock_trello.models.AccountUser;

public interface AccountUserRepository extends CrudRepository<AccountUser, byte[]>{
	Optional<AccountUser> findByEmail(String email);
}
 