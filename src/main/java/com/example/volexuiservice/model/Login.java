package com.example.volexuiservice.model;

import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class Login {
	@NotNull(message = "The Email cannot be null")
	private String email;
	@NotNull(message = "The Password cannot be null")
	private String password;
	private String status;
	private String role;

}
