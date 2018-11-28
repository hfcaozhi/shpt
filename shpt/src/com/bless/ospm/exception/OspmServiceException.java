package com.bless.ospm.exception;

import com.bless.common.exception.ServiceException;

public class OspmServiceException extends ServiceException{
	private static final long serialVersionUID = 9036292662980966486L;

	public OspmServiceException() {
		super();
	}

	public OspmServiceException(String message, Throwable cause) {
		super(message, cause);
	}

	public OspmServiceException(String message) {
		super(message);
	}

	public OspmServiceException(Throwable cause) {
		super(cause);
	}
	
	
}
