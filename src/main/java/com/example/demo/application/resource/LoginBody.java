package com.example.demo.application.resource;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class LoginBody {

	@NotBlank
	@Size(max = 18)
	private String id;

	@NotBlank
	@Size(max = 99)
	private String password;


}
