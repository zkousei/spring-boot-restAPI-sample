package com.example.demo.application.resource;

import lombok.Data;

@Data
public class SampleResponse {


	private boolean result;
	private String message;

	public SampleResponse(boolean result, String message) {
		this.message = message;
		this.result = result;
	}

}
