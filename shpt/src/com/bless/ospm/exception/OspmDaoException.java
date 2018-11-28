package com.bless.ospm.exception;

import com.bless.common.exception.DaoException;

public class OspmDaoException extends DaoException{
	private static final long serialVersionUID = -261193140266405961L;

	public OspmDaoException() {
		super();
	}

	public OspmDaoException(String message, Throwable cause) {
		super(message, cause);
	}

	public OspmDaoException(String message) {
		super(message);
	}

	public OspmDaoException(Throwable cause) {
		super(cause);
	}
	
	
}
