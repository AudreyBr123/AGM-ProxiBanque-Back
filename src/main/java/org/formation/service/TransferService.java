package org.formation.service;

import org.formation.dto.TransferDtoRequest;

public interface TransferService {

	public void executeTransfer(TransferDtoRequest transfer);
	
}

