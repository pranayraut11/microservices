package com.ecors.core.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class UserNotFoundException extends ResponseStatusException {

	private static final long serialVersionUID = 1L;

	public UserNotFoundException(String message) {
		super(HttpStatus.NOT_FOUND, message);
	}

	public UserNotFoundException() {
		super(HttpStatus.NOT_FOUND, "User not found");
	}

}
