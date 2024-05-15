package org.formation.service;

import java.util.ArrayList;
import java.util.List;

import org.formation.model.Address;
import org.formation.model.Client;
import org.formation.repository.ClientRepository;
import org.springframework.stereotype.Service;

@Service
public class ClientServiceImpl implements ClientService {
	private List<Client> clients = new ArrayList<>();
	private ClientRepository repository;
	
	public ClientServiceImpl(ClientRepository repository) {
		this.repository = repository;
	}
	
//	public ClientServiceImpl() {
//		
//		Address genericAddress = new Address("rue de la paix", "75000", "Paris");
//		
//		
//		clients.addAll(List.of(
//				new Client("Audrey", "Boureau", "a.boureau@gmail.com", "0000000", genericAddress),
//				new Client("Gwendal", "Breton", "g.breton@gmail.com", "0000000", genericAddress),
//				new Client("Marine", "Spaak", "m.spaak@gmail.com", "0000000", genericAddress)
//				));
//		}

	@Override
	public List<Client> getClients() {
		return repository.findAll();
	}

}
