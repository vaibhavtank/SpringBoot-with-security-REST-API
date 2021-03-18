package com.spring.util;

import org.springframework.validation.Errors;

public class ValidationUtil {
	
	private ValidationUtil() {
	}
	
	
	public static String fromBindingErrors(Errors errors) {
		return errors.getAllErrors().get(0).getDefaultMessage();
	}
}
