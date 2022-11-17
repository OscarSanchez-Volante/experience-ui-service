package com.example.volexuiservice.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.Aggregation;
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

	
	/* 
	 * findALl filtros y paginacion
	 */
	
	@Query("{ 'status': {$ne: 'removed' }}")
	Page<Account> findAll(Pageable pageable);
	
	@Query("{ 'status': {$ne: 'removed' }, $or:[ { 'firstName': {$regex: /?0/, $options:'i' } }, { 'lastName': {$regex: /?0/, $options:'i' } } ]  }")
	Page<Account> findByUser(String user, Pageable pageable);
	
	@Query("{ 'status': {$ne: 'removed' }, 'email': {$regex: /?0/, $options:'i' }}")
	Page<Account> findByEmail(String email, Pageable pageable);
	
	@Query("{'institution': {$regex: /?0/, $options:'i' },'status': {$ne: 'removed' }}")
	Page<Account> findByCompany(String institution, Pageable pageable);
	
	@Query("{'institution': {$regex: /?2/, $options:'i' }, 'email': {$regex: /?1/, $options:'i' }, 'status': {$ne: 'removed' },  $or:[ { 'firstName': {$regex: /?0/, $options:'i' } }, { 'lastName': {$regex: /?0/, $options:'i' } } ] }")
	Page<Account> findAllFilters(String user, String email, String company,Pageable pageable);
	
	@Query("{ 'email': {$regex: /?1/, $options:'i' }, 'status': {$ne: 'removed' }, $or:[ { 'firstName': {$regex: /?0/, $options:'i' } }, { 'lastName': {$regex: /?0/, $options:'i' } } ]  }")
	Page<Account> findByUserEmail(String user, String email, Pageable pageable);
	
	@Query("{ 'institution': {$regex: /?1/, $options:'i' }, 'status': {$ne: 'removed' }, $or:[ { 'firstName': {$regex: /?0/, $options:'i' } }, { 'lastName': {$regex: /?0/, $options:'i' } } ]}")
	Page<Account> findByUserCompany(String user, String company, Pageable pageable);
	
	@Query("{'institution': {$regex: /?1/, $options:'i' }, 'email': {$regex: /?0/, $options:'i' }, 'status': {$ne: 'removed' } }")
	Page<Account> findByEmailCompany(String email, String company, Pageable pageable);

	@Query(value = "{}", count = true)
	Optional<Long> countAllAccounts();

	@Query(value = "{'status': ?0}", count = true)
	Optional<Long> countActiveAccounts(String status);

	@Aggregation(pipeline = { "{ '$group': { '_id' : '$institution' } }" })
	Optional<List<String>> countInstitutionsAccounts();
}
