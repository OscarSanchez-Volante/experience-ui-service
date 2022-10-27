package com.example.volexuiservice.service;

import com.example.volexuiservice.exception.AccountCollectionException;
import com.example.volexuiservice.model.Account;

import java.util.List;

import javax.validation.ConstraintViolationException;

public interface AccountService {
	
	public void createAccount(Account account) throws ConstraintViolationException, AccountCollectionException;
	
	public List<Account> getAllAccounts();

}
