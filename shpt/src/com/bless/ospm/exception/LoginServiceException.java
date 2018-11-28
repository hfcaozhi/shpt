package com.bless.ospm.exception;

import com.bless.common.exception.ServiceException;

public class LoginServiceException extends ServiceException{
	private static final long serialVersionUID = 9036292662980966486L;

	public LoginServiceException() {
		super();
	}

	public LoginServiceException(String message, Throwable cause) {
		super(message, cause);
	}

	public LoginServiceException(String message) {
		super(message);
	}

	public LoginServiceException(Throwable cause) {
		super(cause);
	}
	
	
}
