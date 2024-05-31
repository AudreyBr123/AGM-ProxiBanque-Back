package org.formation.service;

import org.formation.dto.TransferDtoRequest;
import org.formation.dto.TransferDtoResponse;
import org.formation.exception.TransferException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;

import jakarta.validation.ConstraintViolationException;

public interface TransferService {
	
	public ResponseEntity<TransferDtoResponse> executeTransfer(TransferDtoRequest transfer)
			throws NullPointerException, TransferException, MethodArgumentNotValidException, ConstraintViolationException;

}
