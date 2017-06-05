package com.indium.service.exception;

public class UserAlreadyExistException extends RuntimeException {

	public UserAlreadyExistException(final String message) {
		super(message);
	}

}
