package com.example.volexuiservice.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.example.volexuiservice.exception.AccountAppException;
import com.example.volexuiservice.exception.ResourceNotFoundException;
import com.example.volexuiservice.model.Account;
import com.example.volexuiservice.model.AccountLoginDTO;
import com.example.volexuiservice.model.Login;
import com.example.volexuiservice.repo.AccountRepository;

@Service
public class LoginServiceImpl implements LoginService {
    
    @Autowired
	private AccountRepository accountRepository;


    @Override
    public AccountLoginDTO doLogin(Login login) {
        AccountLoginDTO accountLoginDTO=null;
        Account account = accountRepository.doLogin(login.getEmail(),login.getPassword())
				.orElseThrow(()-> new AccountAppException(HttpStatus.NOT_FOUND, 404,"Account not found, please check login information is correct.",false) );
        System.out.println(account.toString());
        accountLoginDTO=AccountLoginDTO.builder().userName(account.getFirstName()+" "+account.getLastName())
        .role(account.getRole()).build();
		return accountLoginDTO;

    }
    
}
