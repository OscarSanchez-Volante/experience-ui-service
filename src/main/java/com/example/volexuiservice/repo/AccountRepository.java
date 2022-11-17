package com.example.volexuiservice.repo;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
	
	@Query("{ 'email' :  ?0, 'password' :  ?1}")
	Optional<Account> validatePassword(String email,String password);
	
	Page<Account> findAll(Pageable pageable);
	
	
	@Query("{$expr: {$eq: [{$concat: ['$firstName', ' ', '$lastName']}, ?0]}}")
	Page<Account> findByUser(String user, Pageable pageable);
	
	Page<Account> findByEmail(String email, Pageable pageable);
	
	@Query("{'institution': ?0}")
	Page<Account> findByCompany(String institution, Pageable pageable);
	
	
	@Query("{'institution':?2, 'email':?1, $expr: {$eq: [{$concat: ['$firstName', ' ', '$lastName']}, ?0]}}")
	Page<Account> findAllFilters(String user, String email, String institution,Pageable pageable);
	
	@Query("{ 'email':?1, $expr: {$eq: [{$concat: ['$firstName', ' ', '$lastName']}, ?0]}}")
	Page<Account> findByUserEmail(String user, String email, Pageable pageable);
	
	@Query("{ 'institution':?1, $expr: {$eq: [{$concat: ['$firstName', ' ', '$lastName']}, ?0]}}")
	Page<Account> findByUserCompany(String user, String company, Pageable pageable);
	
	@Query("{'institution':?1, 'email':?0 }")
	Page<Account> findByEmailCompany(String email, String company, Pageable pageable);

}
