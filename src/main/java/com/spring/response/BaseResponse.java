package com.spring.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.spring.enums.Response;

@JsonInclude(value = Include.NON_NULL)
public class BaseResponse {

	public Response status;
	public Long id;
	public Object data;
	
	public BaseResponse(Response status, Long id) {
		super();
		this.status = status;
		this.id = id;
	}

	public BaseResponse() {
		this.status = Response.OK;
	}

	public BaseResponse(Long id) {
		this.id = id;
	}

	public BaseResponse(Response status) {
		this.status = status;
	}

	public Response getStatus() {
		return status;
	}

	public void setStatus(Response status) {
		this.status = status;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

}
