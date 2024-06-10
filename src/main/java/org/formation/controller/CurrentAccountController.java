package org.formation.controller;

import java.util.List;
import java.util.Optional;

import org.formation.model.CurrentAccount;
import org.formation.service.CurrentAccountServiceImpl;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/currentAccounts")
public class CurrentAccountController {

	CurrentAccountServiceImpl service;

	public CurrentAccountController(CurrentAccountServiceImpl service) {
		this.service = service;
	}

	@GetMapping
	public List<CurrentAccount> getCurrentAccounts() {
		return service.getAll();
	}

	@GetMapping("{id}")
	public Optional<CurrentAccount> getCurrentAccount(@PathVariable Long id) {
		return service.getById(id);
	}

}
