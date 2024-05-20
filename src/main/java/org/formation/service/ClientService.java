package org.formation.service;

import java.util.List;

import org.formation.model.Client;

public interface ClientService {
	List<Client> getClients();
	Client save(Client client);
	void deleteById(Long id);
}
