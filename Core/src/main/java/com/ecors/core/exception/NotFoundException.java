package com.ecors.core.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class NotFoundException extends ResponseStatusException {

	private static final long serialVersionUID = 1L;
	public static final String MESSAGE=" not found";

	public NotFoundException(String entity) {
		super(HttpStatus.NOT_FOUND, entity+MESSAGE);
	}
	
	public NotFoundException(String entity,String id) {
		super(HttpStatus.NOT_FOUND, entity+" with "+id+MESSAGE);
	}

	public NotFoundException() {
		super(HttpStatus.NOT_FOUND, "Entity not found");
	}

}
