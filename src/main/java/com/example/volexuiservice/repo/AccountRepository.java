package com.example.volexuiservice.repo;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.volexuiservice.model.Account;

@Repository
public interface AccountRepository extends MongoRepository<Account, String> {

	@Query("{'email': ?0}")
	Optional<Account> findByEmail(String email);

}