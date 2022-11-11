package com.example.volexuiservice.dto;

import org.springframework.http.HttpStatus;

public class SuccessResponse {

	private Object data;
	private Boolean success;
	private HttpStatus status;
	private int code;
	private String message;

	public SuccessResponse(Object data, Boolean success, HttpStatus status, int code) {
		super();
		this.data = data;
		this.success = success;
		this.status = status;
		this.code = code;
	}
	
	public SuccessResponse(String message, Boolean success, HttpStatus status, int code) {
		super();
		this.message = message;
		this.success = success;
		this.status = status;
		this.code = code;
	}

	public SuccessResponse(Object data, Boolean success, HttpStatus status, int code,String message) {
		super();
		this.data = data;
		this.success = success;
		this.message = message;
		this.status = status;
		this.code = code;
	}
	
	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public Boolean getSuccess() {
		return success;
	}

	public void setSuccess(Boolean success) {
		this.success = success;
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

}
