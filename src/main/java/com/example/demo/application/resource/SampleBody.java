package com.example.demo.application.resource;


import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;



@Data
public class SampleBody {

	@NotNull
	@Size(max = 10)
	private String param;



}
