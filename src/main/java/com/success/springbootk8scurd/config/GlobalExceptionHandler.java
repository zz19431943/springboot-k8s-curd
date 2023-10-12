package com.success.springbootk8scurd.config;

import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

import javax.persistence.EntityNotFoundException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<?> handleValidationErrors(MethodArgumentNotValidException ex) {
		Map<String, String> map = new HashMap<>();

		ex.getBindingResult().getFieldErrors().forEach(error -> {
			map.put(error.getField(), error.getDefaultMessage());
		});

		return new ResponseEntity<>(map, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(NoSuchElementException.class)
	public ResponseEntity<?> handleNoSuchElementException(NoSuchElementException exception) {
		return new ResponseEntity<>("No such element with this ID", HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(EntityNotFoundException.class)
	public ResponseEntity<?> handleEntityNotFoundException(EntityNotFoundException e) {
		return new ResponseEntity<>("No such element with this ID", HttpStatus.NOT_FOUND);
	}
	/*
	 * @ExceptionHandler(ConstraintViolationException.class) public
	 * ResponseEntity<?>
	 * handleConstraintViolationException(ConstraintViolationException e) { return
	 * new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST); }
	 */	

}
