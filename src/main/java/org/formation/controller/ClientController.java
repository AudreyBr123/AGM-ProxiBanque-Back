package org.formation.controller;

import java.net.URI;
import java.util.List;

import org.formation.model.Advisor;
import org.formation.model.Client;
import org.formation.service.ClientService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PutMapping;


@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/clients")
public class ClientController {
	private static final Logger LOG = LoggerFactory.getLogger(ClientController.class);
	private ClientService service;
	
	public ClientController(ClientService service) {
		this.service = service;
	}
	
	@GetMapping
	public List<Client> getClients() {
		LOG.debug("======================> Accès à la liste des clients depuis le back-end");
		return service.getAll();
	}
	
	@GetMapping("{id}")
	public ResponseEntity<Client> getClient(@PathVariable Long id) {
		Client client = service.getById(id);				
		if (client == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok().body(client);		 
	}
	
	@PostMapping
	public Client postClient (@RequestBody Client client) {
		return service.save(client);		
	}

	@PutMapping("{id}")
	public ResponseEntity<Client> putClient(@PathVariable Long id, @RequestBody Client client) {
		Client oldClient = service.getById(id);

		if(oldClient == null) {
			Client savedClient = service.save(client);

			return ResponseEntity
				.created(URI.create("/clients/"+savedClient.getId()))
				.body(savedClient);
		}
		
		Client newClient = service.update(client);
		return ResponseEntity.ok(newClient);
	}
	
	@PutMapping("{clientId}/advisor/{advisorId}")
	public ResponseEntity<Client> assignAdvisorToClient(@PathVariable Long clientId, @PathVariable Long advisorId) {
		Client oldClient = service.getById(clientId);
		
		if (service.assignAdvisorToClient(clientId, advisorId) == null) {
			// BUG ceci ne semble pas fonctionner pour le moment
			return ResponseEntity.notFound().build();
		}
		
		service.assignAdvisorToClient(clientId, advisorId);
		return ResponseEntity.ok(service.getById(clientId));
	}
	
	@DeleteMapping("{id}")
	void deleteClient(@PathVariable Long id) {	
		service.deleteById(id);
	}
}
