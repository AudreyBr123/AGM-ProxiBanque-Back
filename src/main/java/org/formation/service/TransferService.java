package org.formation.service;

import org.formation.dto.TransferDtoRequest;
import org.formation.dto.TransferDtoResponse;
import org.springframework.http.ResponseEntity;

public interface TransferService {

	public ResponseEntity<TransferDtoResponse> executeTransfer(TransferDtoRequest transfer) throws NullPointerException, TransferException;
	
}

