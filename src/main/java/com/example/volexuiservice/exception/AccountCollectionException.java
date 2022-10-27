package com.example.volexuiservice.exception;

public class AccountCollectionException extends Exception {

	
	private static final long serialVersionUID = 1L;

	public AccountCollectionException(String message) {
		super(message);
	}
	
	public static String NotFoundException(String id) {
		return "Account with "+id+" not found!";
	}
	
	public static String AccountAlreadyExists() {
		return "Account with given email already exists";
	}
	
}
