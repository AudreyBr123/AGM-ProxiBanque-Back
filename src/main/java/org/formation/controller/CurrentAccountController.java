package org.formation.controller;

import java.util.List;
import java.util.Optional;

import org.formation.model.CurrentAccount;
import org.formation.service.CurrentAccountServiceImpl;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
//@CrossOrigin(origins = "http://localhost:4200")
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
	
	//TO DO : changer le type de retour en response entity
	@GetMapping("{id}")
	public Optional<CurrentAccount> getCurrentAccount(@PathVariable Long id) {
		return service.getById(id);
	}
	
	//Pas de post car on ne peut pas post un current account sans client
	//Par contre, put le fait si l'id n'existe pas

	//Attention, il faut un id dans le body de RESTer
//	@PutMapping("{id}")
//	public CurrentAccount updateAccount(@PathVariable Long id, @RequestBody CurrentAccount currentAccount) {
//		return service.save(currentAccount);		
//	}
	
	
}
