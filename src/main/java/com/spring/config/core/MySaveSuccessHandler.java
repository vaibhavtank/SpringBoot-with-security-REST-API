package com.spring.config.core;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;
import com.spring.enums.Response;
import com.spring.response.BaseResponse;
import com.spring.response.SuccessResponse;

@Component
public class MySaveSuccessHandler  extends SimpleUrlAuthenticationSuccessHandler {
	
	private Gson gson = new Gson();
	
	
    @Override
    public void onAuthenticationSuccess(
      HttpServletRequest request,
      HttpServletResponse response, 
      Authentication authentication) 
      throws ServletException, IOException {
    	String JSESSIONID = "";
    	Cookie[] cookies = request.getCookies();
    	if(cookies != null){
    	     for(Cookie cookie : cookies){
	    	      if(cookie.getName().equals("JSESSIONID")){
	    	       JSESSIONID = cookie.getValue();
	    	       break;
	    	      }
    	     }
    	}
    	
    	SuccessResponse output = new SuccessResponse(Response.OK,JSESSIONID);
        String employeeJsonString = this.gson.toJson(output);
        PrintWriter out = response.getWriter();
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        out.print(employeeJsonString);
        out.flush();   
    }
}
