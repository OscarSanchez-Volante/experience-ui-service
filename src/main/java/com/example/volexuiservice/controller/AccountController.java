package com.example.volexuiservice.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.validation.ConstraintViolationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.volexuiservice.exception.AccountCollectionException;
import com.example.volexuiservice.model.Account;
import com.example.volexuiservice.repo.AccountRepository;
import com.example.volexuiservice.service.AccountService;

@CrossOrigin
@RestController
@RequestMapping("/account")
public class AccountController {
	
	@Autowired
	private AccountRepository accountRepository;
	
	@Autowired
	private AccountService accountService;
	
	@GetMapping("")
	public ResponseEntity<?> getAllAccounts(){  
		
		List<Account> accounts = accountService.getAllAccounts();
		Map<String, Object> response = new HashMap<>();
		
		if( accounts.size() > 0 ) {
			response.put("success", true);
			response.put("data", accounts);
			response.put("message", HttpStatus.OK);
		}else {
			response.put("success", false);
			response.put("message", "No accounts available");
		}
		
		return new ResponseEntity<>(response, accounts.size() > 0 ? HttpStatus.OK: HttpStatus.NOT_FOUND);
		
	}
	
	
	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping("")
	public ResponseEntity<?> createAccount(@RequestBody Account account){
		
		Map<String, Object> response = new HashMap<>();
		try {

			accountService.createAccount(account);
			response.put("success", true);
			response.put("data", account);
			response.put("message", HttpStatus.CREATED);
			return new ResponseEntity<>(response, HttpStatus.CREATED);
			
		}catch(ConstraintViolationException e) {
			
			response.put("success", false);
			response.put("message", e.getMessage());
			//response.put("error", HttpStatus.UNPROCESSABLE_ENTITY);
			//response.put("status", HttpStatus.UNPROCESSABLE_ENTITY.value());
			
			return new ResponseEntity<>(response, HttpStatus.UNPROCESSABLE_ENTITY);
		}catch(AccountCollectionException e) {
			
			response.put("success", false);
			response.put("message", e.getMessage());
			//response.put("error", HttpStatus.CONFLICT);
			//response.put("status", HttpStatus.CONFLICT.value());
			return new ResponseEntity<>(response, HttpStatus.CONFLICT);
			
		}
	}
	
	@GetMapping("{id}")
	public ResponseEntity<?> getAccountById(@PathVariable("id") String id){
		Optional<Account> accountOptional = accountRepository.findById(id);
		Map<String, Object> response = new HashMap<>();
		if(accountOptional.isPresent()) {
			return new ResponseEntity<>(accountOptional.get(),HttpStatus.OK);
		}else {
			
			response.put("success", false);
			response.put("message", "Account not found with id "+id);
			//response.put("error", HttpStatus.NOT_FOUND);
			//response.put("status", HttpStatus.NOT_FOUND.value());
			return new ResponseEntity<>(response,HttpStatus.NOT_FOUND);
			
		}
	}
	
	
	@PutMapping("{id}")
	public ResponseEntity<?> updateAccount(@PathVariable String id, @RequestBody Account account){
		Optional<Account> accountOptional = accountRepository.findById(id);
		
		Map<String, Object> response = new HashMap<>();
		
		if(accountOptional.isPresent()) {
			Account accountSave = accountOptional.get();
			
			accountSave.setInstitution(account.getInstitution() !=null ? account.getInstitution() : accountSave.getInstitution() );
			accountSave.setFirstName(account.getFirstName() !=null ? account.getFirstName() : accountSave.getFirstName() );
			accountSave.setLastName(account.getLastName() !=null ? account.getLastName() : accountSave.getLastName() );
			accountSave.setTitle(account.getTitle() !=null ? account.getTitle() : accountSave.getTitle() );
			accountSave.setCountry(account.getCountry() !=null ? account.getCountry() : accountSave.getCountry() );
			accountSave.setPhone(account.getPhone() !=null ? account.getPhone() : accountSave.getPhone() );
			accountSave.setEmail(account.getEmail() != null ? account.getEmail() : accountSave.getEmail() );
			accountSave.setPassword(account.getPassword() != null ? account.getPassword() : accountSave.getPassword() );
			
			accountRepository.save(accountSave);
			
			
			response.put("success", true);
			response.put("data", accountSave);
			response.put("message", HttpStatus.OK);
		
			
			return new ResponseEntity<>(response,HttpStatus.OK);
			
		}else {
			
			response.put("success", false);
			response.put("message", "Account not found with id "+id);
			
			return new ResponseEntity<>(response,HttpStatus.NOT_FOUND);
		}
	}
	
	@DeleteMapping("{id}")
	public ResponseEntity<?> delete(@PathVariable("id") String id ){
		Map<String, Object> response = new HashMap<>();
		
		try {
			accountRepository.deleteById(id);
			
			response.put("success", true);
			response.put("message", "Succesfully deleted with id "+id);
			
			return new ResponseEntity<>(response, HttpStatus.OK);
			
		}catch(Exception e) {
			response.put("success", false);
			response.put("message", e.getMessage());
			
			return new ResponseEntity<>(response,HttpStatus.NOT_FOUND);
		}
	}
	


}
