package com.example.volexuiservice.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AccountLoginDTO {
    private String userName;
    private String role;
    
}
