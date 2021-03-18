package com.spring.exception;

public class AuthenticationException extends AppException {

	private static final long serialVersionUID = -5904437867887712745L;

	public AuthenticationException() {
		super();
	}

	public AuthenticationException(String messageKey, Object... args) {
		super(messageKey, args);
	}

}
