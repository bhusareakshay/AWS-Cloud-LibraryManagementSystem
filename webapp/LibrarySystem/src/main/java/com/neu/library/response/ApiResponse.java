package com.neu.library.response;

import org.springframework.http.HttpStatus;

public class ApiResponse {
	private HttpStatus status;
	private String successMessage;
	private String errorMessage;
	
	public ApiResponse(HttpStatus status, String successMessage, String errorMessage) {
		super();
		this.status = status;
		this.successMessage = successMessage;
		this.errorMessage = errorMessage;
	}
	public HttpStatus getStatus() {
		return status;
	}

	public void setStatus(HttpStatus status) {
		this.status = status;
	}
	public String getSuccessMessage() {
		return successMessage;
	}
	public void setSuccessMessage(String successMessage) {
		this.successMessage = successMessage;
	}
	public String getErrorMessage() {
		return errorMessage;
	}
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
}
