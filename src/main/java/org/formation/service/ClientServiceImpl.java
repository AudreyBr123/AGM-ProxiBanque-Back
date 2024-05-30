package org.formation.service;

import java.util.List;

import org.formation.model.Advisor;
import org.formation.model.Client;
import org.formation.repository.AdvisorRepository;
import org.formation.repository.ClientRepository;
import org.springframework.stereotype.Service;

@Service
public class ClientServiceImpl implements ClientService {
//	private List<Client> clients = new ArrayList<>();
	private ClientRepository repository;
	private AdvisorRepository advisorRepository;
	
	public ClientServiceImpl(ClientRepository repository, AdvisorRepository advisorRepository) {
		this.repository = repository;
		this.advisorRepository = advisorRepository;
	}
	
//	public ClientServiceImpl() {
//		
//		Address genericAddress = new Address("rue de la paix", "75000", "Paris");
//		CurrentAccount genericCurrentAccount = new CurrentAccount(122.2);
//		SavingAccount genericSavingAccount = new SavingAccount(122.2);
//
//		
//		clients.addAll(List.of(
//				new Client("Audrey", "Boureau", "a.boureau@gmail.com", "0000000", genericAddress, genericCurrentAccount, genericSavingAccount),
//				new Client("Gwendal", "Breton", "g.breton@gmail.com", "0000000", genericAddress, genericCurrentAccount, genericSavingAccount),
//				new Client("Marine", "Spaak", "m.spaak@gmail.com", "0000000", genericAddress, genericCurrentAccount, genericSavingAccount)
//				));
//		}

	@Override
	public List<Client> getAll() {
		return repository.findAll();
	//	return clients;
	}

	@Override
	public Client save(Client client) {
		return repository.save(client);
	}

	@Override
	public void deleteById(Long id) {
		repository.deleteById(id);
	}

	@Override
	public Client update(Client client) {
		return repository.save(client);
	}
    
  	@Override
	public Client getById(Long id) {
		return repository.findById(id).orElse(null);
	}
  	
  	@Override
  	public Client assignAdvisorToClient(Long clientId, Long advisorId) {
  		Client client = repository.findById(clientId).orElse(null);
  		Advisor advisor = advisorRepository.findById(advisorId).orElse(null);
  		
  		if (client == null || advisor == null) {
  			return null;
  		}
  		
  		client.setAdvisor(advisor);
  		repository.save(client);
  		return client;
  	}

}
