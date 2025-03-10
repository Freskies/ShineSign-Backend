package org.shinesignbackend.exceptions;

import io.jsonwebtoken.MalformedJwtException;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionHandlerClass {
	@ExceptionHandler (value = EntityNotFoundException.class)
	protected ResponseEntity<Error> entityNotFound (@NotNull EntityNotFoundException ex) {
		Error error = new Error(
			"Entity not found",
			ex.getMessage(),
			"404"
		);
		return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler (value = EntityExistsException.class)
	protected ResponseEntity<Error> alreadyExistsException (@NotNull EntityExistsException ex) {
		Error error = new Error(
			"Entity already exists",
			ex.getMessage(),
			"409"
		);
		return new ResponseEntity<>(error, HttpStatus.CONFLICT);
	}

	@ExceptionHandler (value = SecurityException.class)
	protected ResponseEntity<Error> securityException (@NotNull SecurityException ex) {
		Error error = new Error(
			"You are not authorized to perform this action",
			ex.getMessage(),
			"403"
		);
		return new ResponseEntity<>(error, HttpStatus.UNAUTHORIZED);
	}

	@ExceptionHandler (value = MalformedJwtException.class)
	protected ResponseEntity<Error> jwtException (@NotNull Exception ex) {
		Error error = new Error(
			"JWT error",
			ex.getMessage(),
			"401"
		);
		return new ResponseEntity<>(error, HttpStatus.UNAUTHORIZED);
	}

	@ExceptionHandler (value = IllegalStateException.class)
	protected ResponseEntity<Error> illegalStateException (@NotNull IllegalStateException ex) {
		Error error = new Error(
			"Invalid state",
			ex.getMessage(),
			"400"
		);
		return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
	}

}