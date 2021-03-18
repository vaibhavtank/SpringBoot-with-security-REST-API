package com.spring.request;

import com.fasterxml.jackson.annotation.JsonProperty;

public class RegisterRequest {

	@JsonProperty(value = "username")
	private String username;
	
	@JsonProperty(value = "emailId")
	private String emailId;

	@JsonProperty(value = "password")
	private String password;
	
	@JsonProperty(value = "country")
	private String country;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}
	
	
}
