package com.spring.exception;

public class AppException extends RuntimeException {

	private static final long serialVersionUID = -4823652780112805484L;
	
	private Object[] args;
	private String messageKey;
	
	public AppException() {
		super();
	}

	public AppException(String message) {
		super(message);
	}

	public AppException(String messageKey, Object... args) {
		this.messageKey = messageKey;
		this.args = args;
	}

	public AppException(String message, Throwable cause) {
		super(message, cause);
	}

	public Object[] getArgs() {
		return args;
	}

	public String getMessageKey() {
		return messageKey;
	}

}
