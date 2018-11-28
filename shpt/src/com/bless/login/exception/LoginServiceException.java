package com.bless.login.exception;

import org.springframework.security.authentication.AuthenticationServiceException;

public class LoginServiceException extends AuthenticationServiceException{
	private static final long serialVersionUID = 1103446495707717513L;

	public LoginServiceException(String message, Throwable cause) {
		super(message, cause);
	}

	public LoginServiceException(String message) {
		super(message);
	}

}
