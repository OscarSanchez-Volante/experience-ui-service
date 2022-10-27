package com.example.volexuiservice.model;

import java.util.Date;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.Data;

@Data
@Document(collection="accounts")
public class Account {
	
	
	@Id
	private String id;
	@NotNull(message="The Institution Name cannot be null")
	private String institution;
	
	@NotNull(message="The First Name cannot be null")
	private String firstName;
	@NotNull(message="The Last Name cannot be null")
	private String lastName;
	@NotNull(message="The Title cannot be null")
	private String title;
	@NotNull(message="The Country cannot be null")
	private String country;
	@NotNull(message="The Phone cannot be null")
	private String phone;
	@NotNull(message="The Email cannot be null")
	@Email
	private String email;
	@NotNull(message="The password cannot be null")
	private String password;
	private Date createdAt;
	private Date updatedAt;


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

}
