package org.formation.controller;

import java.util.List;

import org.formation.model.Advisor;
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
		LOG.debug("Accès au conseiller d'id " +id);
		return service.getById(id).map(c -> ResponseEntity.ok().body(c))
		.orElse(ResponseEntity.notFound().build());
	}
	
	@PostMapping
	public Advisor postAdvisor(@RequestBody Advisor advisor) {
		LOG.debug("Création du conseiller " + advisor.getPersonInfos().getFirstName());
		return service.save(advisor);
	}

}
