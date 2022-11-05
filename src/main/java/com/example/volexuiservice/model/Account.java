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
	
	@NotEmpty
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
			Date createdAt, Date updatedAt) {
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

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getInstitution() {
		return institution;
	}

	public void setInstitution(String institution) {
		this.institution = institution;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
