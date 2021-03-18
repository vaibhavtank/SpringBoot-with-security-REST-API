package com.spring.exception;

public class BadRequestException extends AppException {

	private static final long serialVersionUID = -6376477319505808931L;

	public BadRequestException() {
		super();
	}

	public BadRequestException(String messageKey, Object... args) {
		super(messageKey, args);
	}

}
