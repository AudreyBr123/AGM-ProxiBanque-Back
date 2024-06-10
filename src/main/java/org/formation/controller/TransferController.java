package org.formation.controller;

import org.formation.dto.TransferDtoRequest;
import org.formation.dto.TransferDtoResponse;
import org.formation.exception.TransferException;
import org.formation.service.TransferServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Valid;

/**
 * Classe qui récupère les requêtes lancées par le front et y répond
 */
@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/transfer")
public class TransferController {

	TransferServiceImpl transferService;
	

	public TransferController(TransferServiceImpl transferService) {
		this.transferService = transferService;
	}

	@PutMapping
	public ResponseEntity<TransferDtoResponse> transfer(@RequestBody @Valid TransferDtoRequest transfer)
			throws MethodArgumentNotValidException {
		return transferService.executeTransfer(transfer);
	}

}
