package com.spring.response;

import com.spring.enums.Response;

public class SuccessResponse extends BaseResponse{
	
	private String JSESSIONID;

	public SuccessResponse(Response status, String JSESSIONID) {
		this.JSESSIONID = JSESSIONID;
		this.status = status;
	}

	public String getJSESSIONID() {
		return JSESSIONID;
	}

	public void setJSESSIONID(String jSESSIONID) {
		JSESSIONID = jSESSIONID;
	}
	
	

}
