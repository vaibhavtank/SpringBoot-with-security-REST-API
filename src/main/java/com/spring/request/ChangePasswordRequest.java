package com.spring.request;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ChangePasswordRequest {

	@JsonProperty(value = "emailId")
	private String emailId;
	
    @JsonProperty(value = "newPassword")
    private String newPassword;
    
    @JsonProperty(value = "oldPassword")
    private String oldPassword;

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
    
    
}
