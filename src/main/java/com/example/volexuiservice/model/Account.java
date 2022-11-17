package com.example.volexuiservice.model;

import java.util.Date;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Builder;
import lombok.Data;

@Data
@Document(collection="accounts")
public class Account {
	
	
	@Id
	private String id;
	@NotEmpty
	@NotNull(message="The Institution Name cannot be null")
	private String institution;
	
	@NotEmpty
	@NotNull(message="The First Name cannot be null")
	private String firstName;
	
	@NotEmpty
	@NotNull(message="The Last Name cannot be null")
	private String lastName;
	
	@NotEmpty
	@NotNull(message="The Title cannot be null")
	private String title;
	
	@NotEmpty
	@NotNull(message="The Country cannot be null")
	private String country;
	
	@NotEmpty(message="The Phone cannot be null")
	@NotNull(message="The Phone cannot be null")
	@Size(min=10,message="The Phone must contain at least 10 digits")
	private String phone;
	
	@NotEmpty
	@NotNull(message="The Email cannot be null")
	@Email(message="Email must be a properly formatted email address")
	private String email;
	
	@NotEmpty
	@NotNull(message="The password cannot be null")
	private String password;
	private String role;
	
	@Builder.Default
	private String status = "pending";
	private Date createdAt;
	private Date updatedAt;
	
	private Date lastLogin;
	


	public Account() {
		super();
	}

	public Account(String id, @NotNull(message = "The Institution Name cannot be null") String institution,
			@NotNull(message = "The First Name cannot be null") String firstName,
			@NotNull(message = "The Last Name cannot be null") String lastName,
			@NotNull(message = "The Title cannot be null") String title,
			@NotNull(message = "The Country cannot be null") String country,
			@NotNull(message = "The Phone cannot be null") String phone,
			@NotNull(message = "The Email cannot be null") @Email String email,
			@NotNull(message = "The password cannot be null") String password, String role, String status,
			Date createdAt, Date updatedAt, Date lastLogin) {
		super();
		this.id = id; 
		this.institution = institution;
		this.firstName = firstName;
		this.lastName = lastName;
		this.title = title;
		this.country = country;
		this.phone = phone;
		this.email = email;
		this.password = password;
		this.role = role;
		this.status = status;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
	}
	



}
