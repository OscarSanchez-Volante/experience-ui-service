package com.example.volexuiservice.model;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class AccountLoginDTO {
	private String userName;
	private String role;


}
