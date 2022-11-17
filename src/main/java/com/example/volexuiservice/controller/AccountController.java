package com.example.volexuiservice.controller;

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
import com.example.volexuiservice.model.Login;
import com.example.volexuiservice.service.AccountService;
import com.example.volexuiservice.service.LoginService;
import com.example.volexuiservice.utilities.AppConstants;
import com.fasterxml.jackson.databind.ser.std.StdKeySerializers.Dynamic;

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
			@RequestParam(value="user", required=false) String user, 
			@RequestParam(value="email", required=false) String email, 
			@RequestParam(value="company", required=false) String company, 
			@RequestParam(value = "page", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER, required = false) int page,
			@RequestParam(value = "size", defaultValue = AppConstants.DEFAULT_SIZE_PAGE, required = false) int size,
			@RequestParam(value = "orderBy", defaultValue = AppConstants.DEFAULT_ORDER_BY, required = false) String orderBy,
			@RequestParam(value = "sortDir", defaultValue = AppConstants.DEFAULt_ORDER_DIR, required = false) String sortDir) {
		
		return accountService.getAllAccounts(user,email,company,page, size, orderBy, sortDir, true);
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
	
	@GetMapping("/status/{status}")
	public ResponseEntity<?> getAccountByStatus(@PathVariable("status") String status){
		
		SuccessResponse succesResponse = new SuccessResponse(accountService.getAccountByStatus(status),true,HttpStatus.OK,HttpStatus.OK.value());
		
		return new ResponseEntity<>(succesResponse,HttpStatus.OK);
	}
	
	@PostMapping("/email")
	public ResponseEntity<?> validateEmail(@RequestBody Login login) {
		
		SuccessResponse succesResponse = new SuccessResponse(loginService.validateEmail(login),true,HttpStatus.OK,HttpStatus.OK.value());
		
		return new ResponseEntity<>(succesResponse,HttpStatus.OK);
	}
	
	@PostMapping("/password")
	public ResponseEntity<?> validatePassword(@RequestBody Login login) {
		
		SuccessResponse succesResponse = new SuccessResponse(loginService.validatePassword(login),true,HttpStatus.OK,HttpStatus.OK.value());
		
		return new ResponseEntity<>(succesResponse,HttpStatus.OK);
	}

	
	@PostMapping("")
	public ResponseEntity<?> createAccount(@Valid @RequestBody Account account){
		
		accountService.createAccount(account);
		
		SuccessResponse succesResponse = new SuccessResponse("Account created successfully",true,HttpStatus.CREATED,HttpStatus.CREATED.value());
		
		return new ResponseEntity<>(succesResponse, HttpStatus.OK);
	}

	@PutMapping("{id}")
	public ResponseEntity<?> updateAccount( @RequestBody Account account, @PathVariable("id") String id) {
		
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

	@PostMapping("/loginAdmin")
	public ResponseEntity<?> loginAccountAdmin(@RequestBody Login login){
		SuccessResponse succesResponse = new SuccessResponse(loginService.doLoginAdmin(login),true,HttpStatus.OK,HttpStatus.OK.value(),"Successful admin login, redirecting to the control panel");
		//SuccessResponse succesResponse = new SuccessResponse(loginService.doLoginAdmin(login),true,HttpStatus.OK,HttpStatus.OK.value());
		return new ResponseEntity<>(succesResponse,HttpStatus.OK);
    }

	public static class updateAccountNameData
	{
		public String id; 
		public String name;  
		public String lastname;  
	};


	@PostMapping("/updateAccountName")
	public ResponseEntity<?> updateAccountName(@RequestBody updateAccountNameData data){
		return new ResponseEntity<>(accountService.updateAccountName(data.id,data.name,data.lastname), HttpStatus.OK);
    }


	public static class updateAccountData
	{
		public String id; 
		public String newvalue;  
	};
	@PostMapping("/updateAccountEmail")
	public ResponseEntity<?> updateAccountEmail(@RequestBody updateAccountData data){
		return new ResponseEntity<>(accountService.updateAccountEmail(data.id,data.newvalue), HttpStatus.OK);
    }

	@PostMapping("/updateAccountPhone")
	public ResponseEntity<?> updateAccountPhone(@RequestBody updateAccountData data){
		return new ResponseEntity<>(accountService.updateAccountPhone(data.id,data.newvalue), HttpStatus.OK);
    }
	
	@PostMapping("/updateAccountPassword")
	public ResponseEntity<?> updateAccountPassword(@RequestBody updateAccountData data){
		return new ResponseEntity<>(accountService.updateAccountPassword(data.id,data.newvalue), HttpStatus.OK);
    }

	@PostMapping("/updateAccountStatus")
	public ResponseEntity<?> updateAccountStatus(@RequestBody updateAccountData data){
		return new ResponseEntity<>(accountService.updateAccountStatus(data.id,data.newvalue), HttpStatus.OK);
    }



}



