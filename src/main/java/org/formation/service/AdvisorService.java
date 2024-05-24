package org.formation.service;

import java.util.List;
import java.util.Optional;

import org.formation.model.Advisor;

public interface AdvisorService {

	List<Advisor> getAll();

	Advisor save(Advisor advisor);
	
	Optional<Advisor> getById(Long id);
	
	

}
