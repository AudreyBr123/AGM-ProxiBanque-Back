package org.formation.service;

import java.util.List;
import java.util.Optional;

import org.formation.exception.AssignAdvisorToClientException;
import org.formation.model.Client;
import org.springframework.http.ResponseEntity;

public interface ClientService {

	/**
	 * Méthode pour retourner la liste des clients
	 */
	List<Client> getAll();

	/**
	 * Méthode pour sauvegarder un nouveau client
	 */
	Client save(Client client);

	/**
	 * Méthode pour supprimer un client
	 */
	void deleteById(Long id);

	/**
	 * Méthode pour trouver un client à partir de son id
	 */
	Optional<Client> getById(Long id);

	/**
	 * Méthode pour modifier les attributs d'un client
	 */
	Client update(Client client);

	/**
	 * Méthode pour assigner un conseiller à un client
	 */
	ResponseEntity<Client> assignAdvisorToClient(Long clientId, Long advisorId) throws AssignAdvisorToClientException;
}
