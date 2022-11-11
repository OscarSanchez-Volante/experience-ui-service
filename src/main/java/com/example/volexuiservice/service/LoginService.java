package com.example.volexuiservice.service;

import java.util.Optional;

import com.example.volexuiservice.model.Account;
import com.example.volexuiservice.model.AccountLoginDTO;
import com.example.volexuiservice.model.Login;

public interface LoginService {

    public AccountLoginDTO doLogin(Login login);

    public AccountLoginDTO doLoginAdmin(Login login);

}
