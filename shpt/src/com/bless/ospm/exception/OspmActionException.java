package com.bless.ospm.exception;

import com.bless.common.exception.ActionException;

public class OspmActionException extends ActionException{
	private static final long serialVersionUID = 5214649175440075936L;

	public OspmActionException() {
		super();
	}

	public OspmActionException(String message, Throwable cause) {
		super(message, cause);
	}

	public OspmActionException(String message) {
		super(message);
	}

	public OspmActionException(Throwable cause) {
		super(cause);
	}
}
