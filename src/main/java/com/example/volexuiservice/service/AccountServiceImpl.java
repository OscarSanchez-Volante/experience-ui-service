package com.example.volexuiservice.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.example.volexuiservice.dto.AccountResponse;
import com.example.volexuiservice.dto.SuccessResponse;
import com.example.volexuiservice.exception.AccountAppException;
import com.example.volexuiservice.exception.ResourceNotFoundException;
import com.example.volexuiservice.model.Account;
import com.example.volexuiservice.repo.AccountRepository;

@Service
public class AccountServiceImpl implements AccountService {
	
	@Autowired
	private AccountRepository accountRepository;
	
	
	
	@Override
	public Account createAccount(Account account) {
		Optional<Account> accountOptional = accountRepository.findByEmail(account.getEmail());
		
		 if( accountOptional.isPresent() ) {
			 throw new AccountAppException(HttpStatus.CONFLICT,HttpStatus.CONFLICT.value(),"Email already exists",false);
		 }
		 
		 account.setCreatedAt( new Date(System.currentTimeMillis()) );
		 Account newAccount = accountRepository.save(account);
		
		 
		 return newAccount;
	}


	@Override
	public AccountResponse getAllAccounts(int pageNumber, int pageSize, String orderBy, String sortDir, Boolean success){
		
		Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name())?Sort.by(orderBy).ascending():Sort.by(orderBy).descending();
		
		Pageable pageable = PageRequest.of(pageNumber, pageSize, sort);
		
		Page<Account> accounts = accountRepository.findAll(pageable);
		
		List<Account> accountList = accounts.getContent();
		
		AccountResponse accountResponse = new AccountResponse();
		
		accountResponse.setRows(accountList);
		accountResponse.setPage(accounts.getNumber());
		accountResponse.setSize(accounts.getSize());
		accountResponse.setCount(accounts.getTotalElements());
		accountResponse.setTotalPages(accounts.getTotalPages());
		accountResponse.setLastPage(accounts.isLast());
		accountResponse.setSuccess( true );
		
		return accountResponse;
		
	}
	

	
	
	@Override
	public Account getAccountByEmail(String email) {
		Account account = accountRepository.findByEmail(email)
				.orElseThrow( ()-> new ResourceNotFoundException("Account","email",email) );
		
		
		return account;
	}
	
	@Override
	public Account getAccountByStatus(String status) {
		Account account = accountRepository.findByStatus(status)
				.orElseThrow( ()-> new ResourceNotFoundException("Account","status",status) );
		
		
		return account;
	}

	
	@Override
	public Account getAccountById(String id) {
		Account account = accountRepository.findById(id)
				.orElseThrow( ()-> new ResourceNotFoundException("Account","id",id) );
		
		
		return account;
	}

	@Override
	public Account updateAccount(Account account, String id) {
		
		Account accountSave = accountRepository.findById(id)
				.orElseThrow( ()-> new ResourceNotFoundException("Account","id",id) );
		
		accountSave.setInstitution(account.getInstitution() !=null ? account.getInstitution() : accountSave.getInstitution() );
		accountSave.setFirstName(account.getFirstName() !=null ? account.getFirstName() : accountSave.getFirstName() );
		accountSave.setLastName(account.getLastName() !=null ? account.getLastName() : accountSave.getLastName() );
		accountSave.setTitle(account.getTitle() !=null ? account.getTitle() : accountSave.getTitle() );
		accountSave.setCountry(account.getCountry() !=null ? account.getCountry() : accountSave.getCountry() );
		accountSave.setPhone(account.getPhone() !=null ? account.getPhone() : accountSave.getPhone() );
		accountSave.setEmail(account.getEmail() != null ? account.getEmail() : accountSave.getEmail() );
		accountSave.setPassword(account.getPassword() != null ? account.getPassword() : accountSave.getPassword() );
		accountSave.setStatus(account.getStatus() != null ? account.getStatus() : accountSave.getStatus() );
		accountSave.setRole(account.getRole()!= null ? account.getRole() : accountSave.getRole());
		
		Account accountUpdate = accountRepository.save(accountSave); 
		return accountUpdate;
		
		
	}
	
	@Override
	public void deleteAccount(String id) {
		Account account = accountRepository.findById(id)
				.orElseThrow( ()-> new ResourceNotFoundException("Account","id",id) );
		
		accountRepository.delete(account);
		
	}


	@Override
	public SuccessResponse updateAccountName(String id, String name, String lastname) {
		Account accountSave = accountRepository.findById(id)
		.orElseThrow( ()-> new ResourceNotFoundException("Account","id",id) );
		accountSave.setFirstName(name);
		accountSave.setLastName(lastname);
		accountRepository.save(accountSave); 
		return new SuccessResponse("Name updated successfully",true, HttpStatus.OK,200);
	}


	@Override
	public SuccessResponse updateAccountEmail(String id, String email) {
		Account accountSave = accountRepository.findById(id)
		.orElseThrow( ()-> new ResourceNotFoundException("Account","id",id) );
		accountSave.setEmail(email);
		accountRepository.save(accountSave); 
		return new SuccessResponse("Email updated successfully",true, HttpStatus.OK,200);
	}


	@Override
	public SuccessResponse updateAccountPhone(String id, String phone) {
		Account accountSave = accountRepository.findById(id)
		.orElseThrow( ()-> new ResourceNotFoundException("Account","id",id) );
		accountSave.setPhone(phone);
		accountRepository.save(accountSave); 
		return new SuccessResponse("Phone updated successfully",true, HttpStatus.OK,200);
	}


	@Override
	public SuccessResponse updateAccountPassword(String id, String pass) {
		Account accountSave = accountRepository.findById(id)
		.orElseThrow( ()-> new ResourceNotFoundException("Account","id",id) );
		accountSave.setPassword(pass);
		accountRepository.save(accountSave); 
		return new SuccessResponse("Password updated successfully",true, HttpStatus.OK,200);
	}


	@Override
	public SuccessResponse updateAccountStatus(String id, String newvalue) {
		Account accountSave = accountRepository.findById(id)
		.orElseThrow( ()-> new ResourceNotFoundException("Account","id",id) );
		accountSave.setStatus(newvalue);
		accountRepository.save(accountSave); 
		return new SuccessResponse("Status updated successfully",true, HttpStatus.OK,200);
	}




	
}
