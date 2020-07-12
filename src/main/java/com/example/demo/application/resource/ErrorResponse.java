package com.example.demo.application.resource;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Value;

@Value
public class ErrorResponse {

	@JsonProperty("result")
	private boolean result;

	@JsonProperty("message")
	private String message;

	@JsonProperty("code")
	private String code;

	public ErrorResponse(boolean result, String message, String code) {
		this.result = result;
		this.message = message;
		this.code = code;
	}

}
