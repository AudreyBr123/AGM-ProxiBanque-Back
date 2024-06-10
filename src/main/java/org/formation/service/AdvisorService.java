package org.formation.service;

import java.util.List;
import java.util.Optional;

import org.formation.model.Advisor;

public interface AdvisorService {

	/**
	 * Méthode pour retourner la liste des conseillers
	 */
	List<Advisor> getAll();

	/**
	 * Méthode pour sauvegarder un nouveau conseiller
	 */
	Advisor save(Advisor advisor);
	
	/**
	 * Méthode pour trouver un conseiller à partir de son id
	 */
	Optional<Advisor> getById(Long id);	

}
