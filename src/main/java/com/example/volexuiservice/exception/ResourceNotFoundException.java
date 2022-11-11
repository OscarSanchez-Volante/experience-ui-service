package com.example.volexuiservice.exception;


public class ResourceNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	private String resource;
	private String field;
	private String value;
	private Boolean success;

	public ResourceNotFoundException(String resource, String field, String value) {
		super(String.format("%s not found with: %s : '%s'", resource, field, value));
		this.resource = resource;
		this.field = field;
		this.value = value;
	}

	public String getResource() {
		return resource;
	}

	public void setResource(String resource) {
		this.resource = resource;
	}

	public String getField() {
		return field;
	}

	public void setField(String field) {
		this.field = field;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public Boolean getSuccess() {
		return success;
	}

	public void setSuccess(Boolean success) {
		this.success = success;
	}

}
