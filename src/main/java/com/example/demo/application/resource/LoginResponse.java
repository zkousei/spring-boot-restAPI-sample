package com.example.demo.application.resource;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Value;

@Value
public class LoginResponse {

	@JsonProperty("result")
	private boolean result;

	@JsonProperty("token")
	private String token;

	public LoginResponse(boolean result, String token) {
		this.result = result;
		this.token = token;
	}

}
