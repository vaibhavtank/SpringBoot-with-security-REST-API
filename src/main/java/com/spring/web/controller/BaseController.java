package com.spring.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.spring.config.MessageConfig;
import com.spring.response.BaseResponse;

public abstract class BaseController {
	
	@Autowired
    public MessageConfig messageConfig;
	
	protected ResponseEntity<BaseResponse> ok(Object data, String messageKey){
        return success(data, messageKey, HttpStatus.OK);
    }

	 protected  ResponseEntity<BaseResponse> success(Object data, String messageKey, HttpStatus httpStatus){
	        BaseResponse entity = new BaseResponse();
	        entity.setData(data);
	        return new ResponseEntity<BaseResponse>(entity, httpStatus);
	    }

	protected ResponseEntity<BaseResponse> badRequest(String messageKey){
		return error(messageKey, HttpStatus.BAD_REQUEST);
	}

	private ResponseEntity<BaseResponse> error(String messageKey, HttpStatus httpStatus) {
		BaseResponse entity = new BaseResponse();

		return new ResponseEntity<BaseResponse>(entity, HttpStatus.NOT_FOUND);
	}

	protected ResponseEntity<BaseResponse> notFound(String messageKey){
		return error(messageKey, HttpStatus.NOT_FOUND);
	}

	protected ResponseEntity<BaseResponse> authentication(String messageKey){
		return error(messageKey, HttpStatus.NOT_ACCEPTABLE);
	}
}
