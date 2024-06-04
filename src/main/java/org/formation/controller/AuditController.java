package org.formation.controller;

import java.util.List;
import java.util.Map;

import org.formation.model.Advisor;
import org.formation.model.CurrentAccount;
import org.formation.service.AuditService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/audit")
public class AuditController {
	private static final Logger LOG = LoggerFactory.getLogger(AuditController.class);
	AuditService service;
	
	public AuditController(AuditService service) {
		this.service = service;
	}

	@GetMapping("/balances")
	public List<Double> getAllBalances() {
		LOG.debug("Accès à la liste de tous les soldes des comptes courants");
		return service.getAllBalances();
	}
	
	@GetMapping("/critical-balances")
	public Map<Long, Double> getCriticalBalances() {
		LOG.debug("Accès à la liste des soldes sous un seuil minimal");
		return service.getCriticalBalances();
	}

}
