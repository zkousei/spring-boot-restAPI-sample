package com.example.demo.domain.exception;

public class InvalidAccessTokenException extends RuntimeException{

	public InvalidAccessTokenException(String message) {
		super(message);
	}


}
