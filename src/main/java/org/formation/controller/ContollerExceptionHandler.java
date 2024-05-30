package org.formation.controller;

import java.util.HashMap;
import java.util.Map;

import org.formation.service.TransferException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ContollerExceptionHandler {

	Map<String, String> errors = new HashMap<>();
	
	@ExceptionHandler(TransferException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	private Map<String, String> handleTransferException(TransferException e) {
			String field = "Transfer Exception";
			String message = e.getMessage();

			errors.put(field, message);

		return errors;
	}
	
	@ExceptionHandler(NullPointerException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	private Map<String, String> handleNullPointerException(NullPointerException e) {
			String field = "Transfer Exception";
			String message = e.getMessage();

			errors.put(field, message);

		return errors;
	}
}
