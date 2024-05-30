package org.formation.controller;

import java.util.HashMap;
import java.util.Map;

import org.formation.exception.TransferException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import jakarta.validation.ConstraintViolationException;

@RestControllerAdvice
public class ContollerExceptionHandler {

	Map<String, String> errors = new HashMap<>();

	@ExceptionHandler(TransferException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	private Map<String, String> handleTransferException(TransferException e) {
		// Vide la map avant d'ajouter un nouveau message d'erreur
		errors.clear();
		
		// Ajoute un message d'erreur
		String field = "TransferException";
		String message = e.getMessage();
		errors.put(field, message);

		return errors;
	}

	@ExceptionHandler(NullPointerException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	private Map<String, String> handleNullPointerException(NullPointerException e) {
		errors.clear();
		String field = "Account";
		String message = e.getMessage();

		errors.put(field, message);

		return errors;
	}
	
	@ExceptionHandler(ConstraintViolationException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	private Map<String, String> handleConstraintViolationException(ConstraintViolationException e) {
		errors.clear();
		String field = "Amount and debitAccount";
		String message = e.getMessage();

		errors.put(field, message);

		return errors;
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	private Map<String, String> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
		errors.clear();
		e.getBindingResult().getAllErrors().forEach(error -> {
			String field = ((FieldError) error).getField();
			String message = error.getDefaultMessage();

			errors.put(field, message);
		});

		return errors;
	}
}
