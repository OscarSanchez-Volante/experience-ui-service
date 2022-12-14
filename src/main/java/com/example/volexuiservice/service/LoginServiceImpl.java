package com.example.volexuiservice.service;


import java.util.Date;
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


    @Override
    public AccountLoginDTO doLoginAdmin(Login login) {
        //AccountLoginDTO accountLoginDTO=new ;
        Account account = accountRepository.doLogin(login.getEmail(),login.getPassword())
				.orElseThrow(()-> new AccountAppException(HttpStatus.NOT_FOUND, 404,"Account not found, please check login information is correct.",false) );
        // System.out.println("----------------------------");
        // System.out.println(account.getRole());
        // System.out.println("/"+account.getRole()+"/");
        // System.out.println(account.getRole()==="admin");
        // System.out.println(account.getRole()!=="admin");
        // System.out.println("----------------------------");
        if(!account.getRole().equals("admin"))
          throw( new AccountAppException(HttpStatus.NOT_FOUND, 404,"Account is not admin account, rejected.",false));
        System.out.println(account.toString());

        var accountLoginDTO=AccountLoginDTO.builder()
        .userName(account.getFirstName())
        .userLastName(account.getLastName())
        .role(account.getRole())
        .email(account.getEmail())
        .id(account.getId())
        .build();
    		return accountLoginDTO;
    }
    
    
    
    @Override
    public AccountLoginDTO validateEmail(Login login) {
    	
    	AccountLoginDTO accountLoginDTO=null;
    	
    	Account account = accountRepository.validateEmail(login.getEmail())
    			.orElseThrow(()-> new AccountAppException(HttpStatus.NOT_FOUND, 404,"The email account doesnt exist",false) );
    	
    	
    	if( !account.getStatus().equals("active")) {
    		throw new AccountAppException(HttpStatus.CONFLICT,HttpStatus.CONFLICT.value(),"The account is not active",false);
    	}
    		
    	
        accountLoginDTO=AccountLoginDTO.builder().userName(account.getEmail()).title(account.getTitle()).firstName(account.getFirstName())
        		.lastName(account.getLastName()).institution(account.getInstitution()).email(account.getEmail()).role(account.getRole()).build();
        
        
		return accountLoginDTO;
    }

	@Override
	public AccountLoginDTO validatePassword(Login login) {
		
    	AccountLoginDTO accountLoginDTO=null;
    	
    	Account account = accountRepository.validatePassword(login.getEmail(),login.getPassword())
    			.orElseThrow(()-> new AccountAppException(HttpStatus.NOT_FOUND, 404,"The password is wrong",false) );
    	
    	System.out.println(account.getId());
    	
    	Account accountUpdate = accountRepository.findById(account.getId())
    			.orElseThrow( ()-> new ResourceNotFoundException("Account","id",account.getId()) );
    	
    	accountUpdate.setLastLogin(new Date(System.currentTimeMillis()) );
		accountRepository.save(accountUpdate); 
    	
    	
        accountLoginDTO=AccountLoginDTO.builder().userName(account.getEmail()).title(account.getTitle()).firstName(account.getFirstName())
        		.lastName(account.getLastName()).institution(account.getInstitution()).email(account.getEmail()).role(account.getRole()).build();
        
        
		return accountLoginDTO;
	}
    
    
}
