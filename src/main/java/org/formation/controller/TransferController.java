package org.formation.controller;

import org.formation.dto.TransferDtoRequest;
import org.formation.dto.TransferDtoResponse;
import org.formation.service.TransferException;
import org.formation.service.TransferServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/transfer")
public class TransferController {

	TransferServiceImpl transferService;

	public TransferController(TransferServiceImpl transferService) {
		this.transferService = transferService;
	}
	
	@PutMapping
	public ResponseEntity<TransferDtoResponse> transfer(@RequestBody @Valid TransferDtoRequest transfer) throws NullPointerException, TransferException {
//		try {
			return transferService.executeTransfer(transfer);						
//		} catch (Exception e) {
//			if (e instanceof NullPointerException) {
//				return ResponseEntity.notFound().build();				
//			}else {
//				return ResponseEntity.badRequest().build();
//			}
//		}
	}

}
