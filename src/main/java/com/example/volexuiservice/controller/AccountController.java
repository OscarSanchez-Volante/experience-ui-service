package com.example.volexuiservice.controller;

import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.volexuiservice.dto.AccountResponse;
import com.example.volexuiservice.dto.SuccessResponse;
import com.example.volexuiservice.model.Account;
import com.example.volexuiservice.model.AccountLoginDTO;
import com.example.volexuiservice.model.Login;
import com.example.volexuiservice.service.AccountService;
import com.example.volexuiservice.service.LoginService;
import com.example.volexuiservice.utilities.AppConstants;

@CrossOrigin
@RestController
@RequestMapping("/account")
public class AccountController {


	@Autowired
	private AccountService accountService;
	@Autowired
	private LoginService loginService;

	@GetMapping("")
	public AccountResponse getAllAccounts(
			@RequestParam(value = "page", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER, required = false) int page,
			@RequestParam(value = "size", defaultValue = AppConstants.DEFAULT_SIZE_PAGE, required = false) int size,
			@RequestParam(value = "orderBy", defaultValue = AppConstants.DEFAULT_ORDER_BY, required = false) String orderBy,
			@RequestParam(value = "sortDir", defaultValue = AppConstants.DEFAULt_ORDER_DIR, required = false) String sortDir) {
		return accountService.getAllAccounts(page, size, orderBy, sortDir, true);
	}

	@GetMapping("{id}")
	public ResponseEntity<?> getAccountById(@PathVariable("id") String id) {
		
		SuccessResponse succesResponse = new SuccessResponse(accountService.getAccountById(id),true,HttpStatus.OK,HttpStatus.OK.value());
		
		return new ResponseEntity<>(succesResponse,HttpStatus.OK);
	}

	@GetMapping("/email/{email}")
	public ResponseEntity<?> getAccountByEmail(@PathVariable("email") String email) {
		
		
		SuccessResponse succesResponse = new SuccessResponse(accountService.getAccountByEmail(email),true,HttpStatus.OK,HttpStatus.OK.value());
		
		return new ResponseEntity<>(succesResponse,HttpStatus.OK);
	}

	
	@PostMapping("")
	public ResponseEntity<?> createAccount(@Valid @RequestBody Account account){
		
		SuccessResponse succesResponse = new SuccessResponse(accountService.createAccount(account),true,HttpStatus.CREATED,HttpStatus.CREATED.value());
		
		return new ResponseEntity<>(succesResponse, HttpStatus.OK);
	}

	@PutMapping("{id}")
	public ResponseEntity<?> updateAccount(@Valid @RequestBody Account account, @PathVariable("id") String id) {
		
		SuccessResponse succesResponse = new SuccessResponse(accountService.updateAccount(account, id),true,HttpStatus.ACCEPTED,HttpStatus.ACCEPTED.value());

		return new ResponseEntity<>(succesResponse, HttpStatus.OK);
	}

	@DeleteMapping("{id}")
	public ResponseEntity<?> deleteAccount(@PathVariable("id") String id) {
		
		accountService.deleteAccount(id);
		
		SuccessResponse succesResponse = new SuccessResponse("Succesfully account deleted with id " + id,true,HttpStatus.NO_CONTENT,HttpStatus.NO_CONTENT.value());

		return new ResponseEntity<>(succesResponse, HttpStatus.OK);
	}

	@PostMapping("/login")
	public ResponseEntity<?> loginAccount(@RequestBody Login login){
		SuccessResponse succesResponse = new SuccessResponse(loginService.doLogin(login),true,HttpStatus.OK,HttpStatus.OK.value());
		
		return new ResponseEntity<>(succesResponse,HttpStatus.OK);
    }

}
