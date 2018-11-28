package com.bless.ospm.dto;

import com.bless.ospm.model.base.User;

public class LoginInfoEditDto {
	private User user;
	private String rePassword;
	
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public String getRePassword() {
		return rePassword;
	}
	public void setRePassword(String rePassword) {
		this.rePassword = rePassword;
	}
}
