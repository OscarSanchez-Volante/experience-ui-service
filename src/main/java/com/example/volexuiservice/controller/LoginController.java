package com.example.volexuiservice.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.volexuiservice.model.Account;
import com.example.volexuiservice.model.AccountLoginDTO;
import com.example.volexuiservice.model.Login;
import com.example.volexuiservice.service.AccountService;
import com.example.volexuiservice.service.LoginService;

@CrossOrigin
@RestController
@RequestMapping("/login")
public class LoginController {

    @Autowired
    LoginService loginService;
    
    @PostMapping()
	public ResponseEntity<?> loginAccount(@RequestBody Login login){
		AccountLoginDTO accountLogin = loginService.doLogin(login);
        
		Map<String, Object> response = new HashMap<>();
		if(accountLogin!=null) {
			response.put("success", true);
			response.put("data", accountLogin);
			response.put("message", HttpStatus.OK);
		}else {
			response.put("success", false);
			response.put("message", "Account no found");
		}

        return new ResponseEntity<>(response, accountLogin != null ? HttpStatus.OK: HttpStatus.NOT_FOUND);	
	}


}
