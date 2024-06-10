package org.formation.controller;

import java.util.List;
import java.util.Set;

import org.formation.model.Advisor;
import org.formation.model.Client;
import org.formation.service.AdvisorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Classe qui récupère les requêtes lancées par le front et y répond
 */
@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/advisors")
public class AdvisorController {

	private static final Logger LOG = LoggerFactory.getLogger(AdvisorController.class);
	AdvisorService service;

	public AdvisorController(AdvisorService service) {
		this.service = service;
	}

	@GetMapping
	public List<Advisor> getAdvisors() {
		LOG.debug("Accès à la liste des conseillers");
		return service.getAll();
	}

	@GetMapping("{id}")
	public ResponseEntity<Advisor> getAdvisor(@PathVariable Long id) {
		LOG.debug("Accès au conseiller d'id " + id);
		return service.getById(id).map(c -> ResponseEntity.ok().body(c)).orElse(ResponseEntity.notFound().build());
	}

	@GetMapping("{id}/number")
	public int getNumberOfClients(@PathVariable Long id) {
		LOG.debug("Nombre de clients du conseiller d'id " + id);
		Advisor advisor = service.getById(id).orElse(null);
		return advisor.getNumberOfClients();
	}

	@GetMapping("{id}/clients")
	public Set<Client> getClientsOfThisAdvisor(@PathVariable Long id) {
		Advisor advisor = service.getById(id).orElse(null);
		return advisor.getClients();
	}

	@PostMapping
	public Advisor postAdvisor(@RequestBody Advisor advisor) {
		LOG.debug("Création du conseiller " + advisor.getPersonInfos().getFirstName());
		return service.save(advisor);
	}
}
