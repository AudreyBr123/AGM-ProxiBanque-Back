package org.formation.controller;

import java.util.HashMap;
import java.util.Map;

import org.formation.exception.AssignAdvisorToClientException;
import org.formation.exception.TransferException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import jakarta.validation.ConstraintViolationException;

/**
 * Classe qui récupère les différentes erreurs lancées par les controlleurs. 
 */
@RestControllerAdvice
public class ContollerExceptionHandler {

	Map<String, String> errors = new HashMap<>();

	/**
	 * Méthode qui gère les exceptions liées aux virements.
	 * Ex : tentative de faire un virement entre les comptes épargnes de deux clients différents.
	 */
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

	/**
	 * Méthode qui gère les valeurs qui ne peuvent pas être persistées.
	 * Ex : le montant indiqué est plus élevé que le solde sur le compte débiteur.
	 */
	@ExceptionHandler(ConstraintViolationException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	private Map<String, String> handleConstraintViolationException(ConstraintViolationException e) {
		errors.clear();
		String field = "Amount and debitAccount";
		String message = e.getMessage();

		errors.put(field, message);

		return errors;
	}

	/**
	 * Méthode qui gère valeurs non valides.
	 * Ex : tentative de faire un virement négatif.
	 */
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
	
	/**
	 * Méthode qui gère les exceptions liées à l'association entre un conseiller et un client
	 * Ex : le client est inconnu
	 */
	@ExceptionHandler(AssignAdvisorToClientException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	private Map<String, String> handleAssignAdvisorToClientException(AssignAdvisorToClientException e) {
		// Vide la map avant d'ajouter un nouveau message d'erreur
		errors.clear();

		// Ajoute un message d'erreur
		String field = "AssignAdvisorToClient Exception";
		String message = e.getMessage();
		errors.put(field, message);

		return errors;
	}
}
