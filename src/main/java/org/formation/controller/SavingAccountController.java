package org.formation.controller;

import java.util.List;

import org.formation.model.SavingAccount;
import org.formation.service.SavingAccountServiceImpl;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/savingAccounts")
public class SavingAccountController {
	SavingAccountServiceImpl service;

	public SavingAccountController(SavingAccountServiceImpl service) {
		this.service = service;
	}
	
	@GetMapping
	public List<SavingAccount> getSavingAccounts() {
		return service.getAll();
	}
}
