package com.example.volexuiservice.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        Account account =  accountRepository.doLogin(login.getEmail(),login.getPassword());
        

        if(account !=null ) {
            return AccountLoginDTO.builder().userName(
                account.getFirstName()+" "+account.getLastName()
            ).build();
		}
        return null;

    }
    
}
