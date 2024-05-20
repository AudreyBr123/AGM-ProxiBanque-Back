package org.formation.service;

import java.util.List;

import org.formation.model.Client;

public interface ClientService {
	List<Client> getAll();
	Client save(Client client);
	Client getById(Long id);
}
