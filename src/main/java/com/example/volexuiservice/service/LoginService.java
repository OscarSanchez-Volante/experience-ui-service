package com.example.volexuiservice.service;

import com.example.volexuiservice.model.AccountLoginDTO;
import com.example.volexuiservice.model.Login;

public interface LoginService {

    public AccountLoginDTO doLogin(Login login);
    
    public AccountLoginDTO validateEmail(Login login);
    
    public AccountLoginDTO validatePassword(Login login);
    
}
