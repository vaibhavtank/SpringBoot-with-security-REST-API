package com.spring.response;

import org.springframework.http.HttpStatus;

import com.spring.enums.Response;

public class FailureResponse extends BaseResponse{
	
	private String errorMessage;
	
	private Integer httpStatusCode;
	
	FailureResponse(){
		super();
	}
	
	public FailureResponse(Response error, String errorMessage, Integer httpStatusCode) {
		this.errorMessage = errorMessage;
		this.httpStatusCode = httpStatusCode;
		this.status = error;
	}

	
	public String getErrorMessage() {
		return errorMessage;
	}
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	public Integer getHttpStatus() {
		return httpStatusCode;
	}
	public void setHttpStatus(Integer httpStatus) {
		this.httpStatusCode = httpStatus;
	}
	
	

}
