package com.spring.exception;

public class ConflictException extends AppException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2410860658384583972L;

	public ConflictException() {
		super();
	}

	public ConflictException(String messageKey, Object... args) {
		super(messageKey, args);
	}

}
