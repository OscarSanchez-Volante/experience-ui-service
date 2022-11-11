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
	
	@Query("{'status': ?0}")
	Optional<Account> findByStatus(String status);

	@Query(value="{ 'email' :  ?0, 'password' :  ?1}")
	Optional<Account> doLogin(String email,String password);
	
	
	@Query("{'email': ?0}")
	Optional<Account> validateEmail(String email);
	
	@Query("{'password': ?0}")
	Optional<Account> validatePassword(String password);

}
