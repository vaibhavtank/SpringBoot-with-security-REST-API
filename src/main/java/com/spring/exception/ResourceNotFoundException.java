package com.spring.exception;

public class ResourceNotFoundException extends AppException {

	private static final long serialVersionUID = -5904437867887712745L;

	public ResourceNotFoundException() {
		super();
	}

	public ResourceNotFoundException(String messageKey, Object... args) {
		super(messageKey, args);
	}

}
