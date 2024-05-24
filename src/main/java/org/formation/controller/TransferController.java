package org.formation.controller;

import org.formation.model.Transfer;
import org.formation.service.TransferServiceImpl;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/transfer")
public class TransferController {

	TransferServiceImpl transferService;

	public TransferController(TransferServiceImpl transferService) {
		this.transferService = transferService;
	}
	
	@PutMapping
	public void transfer(@RequestBody Transfer transfer) {
		transferService.executeTransfer(transfer);
	}

}
