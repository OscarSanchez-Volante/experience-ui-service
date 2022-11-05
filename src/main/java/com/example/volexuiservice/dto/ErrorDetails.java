package com.example.volexuiservice.dto;

import java.util.Date;
import java.util.Map;

import org.springframework.http.HttpStatus;

public class ErrorDetails {

	private Date timeStamp;
	private String message;
	private Boolean success;
	private HttpStatus status;
	private int code;

	public ErrorDetails(Date timeStamp, String message, Boolean success, HttpStatus status, int code) {
		super();
		this.timeStamp = timeStamp;
		this.message = message;
		this.success = success;
		this.status = status;
		this.code = code;
	}
	

	public Date getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(Date timeStamp) {
		this.timeStamp = timeStamp;
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

}
