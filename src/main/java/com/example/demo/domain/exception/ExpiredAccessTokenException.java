package com.example.demo.domain.exception;

public class ExpiredAccessTokenException extends RuntimeException {

	public ExpiredAccessTokenException(String message) {
		super(message);
	}


}
