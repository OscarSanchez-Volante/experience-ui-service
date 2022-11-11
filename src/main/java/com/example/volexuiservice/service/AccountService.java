package com.example.volexuiservice.service;

import com.example.volexuiservice.dto.AccountResponse;
import com.example.volexuiservice.model.Account;



public interface AccountService {
	
	
	public Account createAccount(Account account);
	
	public AccountResponse getAllAccounts(int page, int size, String orderBy, String sortDir, Boolean success);
	
	public Account getAccountByEmail(String email);
	
	public Account getAccountByStatus(String status);
	
	public Account getAccountById(String id);
	
	public Account updateAccount(Account account, String id);
	
	public void deleteAccount( String id );

}
