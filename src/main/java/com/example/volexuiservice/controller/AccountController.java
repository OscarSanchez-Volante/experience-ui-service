package com.example.volexuiservice.controller;

import java.util.Date;
import java.util.List;
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
		//List<Account> accounts = accountRepository.findAll();
		List<Account> accounts = accountService.getAllAccounts();
		
		return new ResponseEntity<>(accounts, accounts.size() > 0 ? HttpStatus.OK: HttpStatus.NOT_FOUND);
		
	}
	
	
	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping("")
	public ResponseEntity<?> createAccount(@RequestBody Account account){
		try {
			//account.setCreatedAt(new Date(System.currentTimeMillis()));
			//accountRepository.save(account);
			accountService.createAccount(account);
			return new ResponseEntity<Account>(account, HttpStatus.OK);
			
		}catch(ConstraintViolationException e) {
			
			return new ResponseEntity<>(e.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY);
		}catch(AccountCollectionException e) {
			
			return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
			
		}
	}
	
	@GetMapping("{id}")
	public ResponseEntity<?> getAccountById(@PathVariable("id") String id){
		Optional<Account> accountOptional = accountRepository.findById(id);
		if(accountOptional.isPresent()) {
			return new ResponseEntity<>(accountOptional.get(),HttpStatus.OK);
		}else {
			return new ResponseEntity<>("Account not found with id "+id,HttpStatus.NOT_FOUND);
		}
	}
	
	
	@PutMapping("{id}")
	public ResponseEntity<?> updateAccount(@PathVariable String id, @RequestBody Account account){
		Optional<Account> accountOptional = accountRepository.findById(id);
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
			
			return new ResponseEntity<>(accountSave,HttpStatus.OK);
			
		}else {
			return new ResponseEntity<>("Account not found with id "+id,HttpStatus.NOT_FOUND);
		}
	}
	
	@DeleteMapping("{id}")
	public ResponseEntity<?> delete(@PathVariable("id") String id ){
		try {
			accountRepository.deleteById(id);
			return new ResponseEntity<>("Succesfully deleted with id "+id, HttpStatus.OK);
		}catch(Exception e) {
			return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
		}
	}
	


}
