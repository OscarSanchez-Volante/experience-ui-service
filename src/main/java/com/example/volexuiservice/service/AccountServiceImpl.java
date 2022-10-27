package com.example.volexuiservice.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.validation.ConstraintViolationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.volexuiservice.exception.AccountCollectionException;
import com.example.volexuiservice.model.Account;
import com.example.volexuiservice.repo.AccountRepository;

@Service
public class AccountServiceImpl implements AccountService {
	
	@Autowired
	private AccountRepository accountRepository;

	@Override
	public void createAccount(Account account) throws ConstraintViolationException, AccountCollectionException {
		// TODO Auto-generated method stub
		Optional<Account> accountOptional =  accountRepository.findByEmail(account.getEmail());
		if( accountOptional.isPresent() ) {
			
			throw new AccountCollectionException(AccountCollectionException.AccountAlreadyExists());
			
		}else {
			account.setCreatedAt( new Date(System.currentTimeMillis()) );
			accountRepository.save(account);
		}
	}
	
	public List<Account> getAllAccounts(){
		List<Account> accounts = accountRepository.findAll();
		if(accounts.size()>0) {
			return accounts;
		}else {
			return new ArrayList<Account>();
		}
	}

}
