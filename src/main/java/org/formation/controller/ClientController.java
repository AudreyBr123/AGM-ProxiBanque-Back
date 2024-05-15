package org.formation.controller;

import java.util.List;

import org.formation.model.Client;
import org.formation.service.ClientService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
//@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/clients")
public class ClientController {
	private ClientService service;
	
	public ClientController(ClientService service) {
		this.service = service;
	}
	
	@GetMapping
	public List<Client> getClients() {
		return service.getClients();
	}
	
	@PostMapping
	public Client postClient (@RequestBody Client client) {
		return service.save(client);		
	}

}
