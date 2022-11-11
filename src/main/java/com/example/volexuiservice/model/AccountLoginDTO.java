package com.example.volexuiservice.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AccountLoginDTO {
    private String id;
    private String userName;
    private String userLastName;
    private String phone;
    private String role;
    private String email;
}
