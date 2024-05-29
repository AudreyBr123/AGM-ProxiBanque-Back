package org.formation.controller;

import org.formation.dto.TransferDtoRequest;
import org.formation.service.TransferServiceImpl;
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
	public void transfer(@RequestBody @Valid TransferDtoRequest transfer) {
		transferService.executeTransfer(transfer);
	}

}
