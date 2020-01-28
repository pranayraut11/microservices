package com.ecors.api.users.config;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.ecors.api.users.ui.response.GenericResponse;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(NumberFormatException.class)
	public final ResponseEntity<GenericResponse<Void>> handleBadRequestException(RuntimeException ex, WebRequest req) {

		return new ResponseEntity<GenericResponse<Void>>(HttpStatus.BAD_REQUEST);
	}
}
