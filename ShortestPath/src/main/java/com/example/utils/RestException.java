package com.example.utils;

import org.springframework.http.HttpStatus;

public class RestException extends RuntimeException{
	
	
	private static final long serialVersionUID = 8404106919665622590L;
	
	private HttpStatus httpStatus;

	public RestException(String message, HttpStatus httpStatus) {
		
		super(message);
		this.httpStatus = httpStatus;
	}

	public HttpStatus getHttpStatus() {
		return httpStatus;
	}

	public void setHttpStatus(HttpStatus httpStatus) {
		this.httpStatus = httpStatus;
	}
	
	

}
