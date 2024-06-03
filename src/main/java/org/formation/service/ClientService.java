package org.formation.service;

import java.util.List;
import java.util.Optional;

import org.formation.exception.AssignAdvisorToClientException;
import org.formation.model.Client;
import org.springframework.http.ResponseEntity;

public interface ClientService {
	List<Client> getAll();
	Client save(Client client);
	void deleteById(Long id);
	Optional<Client> getById(Long id);
	Client update(Client client);
	ResponseEntity<Client> assignAdvisorToClient(Long clientId, Long advisorId) throws AssignAdvisorToClientException;
}
