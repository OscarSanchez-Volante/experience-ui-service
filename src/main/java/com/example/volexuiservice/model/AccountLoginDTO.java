package com.example.volexuiservice.model;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class AccountLoginDTO {
    private String id;
	private String userName;
    private String userLastName;
    private String phone;
	private String role;
	private String title;
	private String firstName;
	private String lastName;
	private String institution;
	private String email;

}
