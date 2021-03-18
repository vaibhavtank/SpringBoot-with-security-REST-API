package com.spring.config.core;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;
import com.spring.enums.Response;
import com.spring.exception.ExceptionResponse;
import com.spring.response.BaseResponse;
import com.spring.response.FailureResponse;

@Component
public class MyFailureHandler extends SimpleUrlAuthenticationFailureHandler {
	
	private Gson gson = new Gson();

	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
            AuthenticationException exception) throws IOException, ServletException {

		FailureResponse failureResponse = new FailureResponse(Response.ERROR,"Bad Credential",HttpStatus.UNAUTHORIZED.value());
		response.setStatus(HttpStatus.UNAUTHORIZED.value());
        String employeeJsonString = this.gson.toJson(failureResponse);
        PrintWriter out = response.getWriter();
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        out.print(employeeJsonString);
        out.flush();
    }
	

}
