package com.example.volexuiservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


public class AccountAppException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	private HttpStatus status;
	private int code;
	private String message;
	private Boolean success;
	
	
	public AccountAppException(HttpStatus status, int code,String message, Boolean success) {
		super();
		this.status = status;
		this.code = code;
		this.success = success;
		this.message = message;
	}


	public HttpStatus getStatus() {
		return status;
	}


	public void setStatus(HttpStatus status) {
		this.status = status;
	}


	public int getCode() {
		return code;
	}


	public void setCode(int code) {
		this.code = code;
	}


	public String getMessage() {
		return message;
	}


	public void setMessage(String message) {
		this.message = message;
	}


	public Boolean getSuccess() {
		return success;
	}


	public void setSuccess(Boolean success) {
		this.success = success;
	}


	
	
	
	
}
