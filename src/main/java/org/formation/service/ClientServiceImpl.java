package org.formation.service;

import java.util.List;
import java.util.Optional;

import org.formation.exception.AssignAdvisorToClientException;
import org.formation.exception.TransferException;
import org.formation.model.Advisor;
import org.formation.model.Client;
import org.formation.repository.AdvisorRepository;
import org.formation.repository.ClientRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ClientServiceImpl implements ClientService {
	private ClientRepository repository;
	private AdvisorRepository advisorRepository;
	
	public ClientServiceImpl(ClientRepository repository, AdvisorRepository advisorRepository) {
		this.repository = repository;
		this.advisorRepository = advisorRepository;
	}
	

	@Override
	public List<Client> getAll() {
		return repository.findAll();
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
	public Optional<Client> getById(Long id) {
		return repository.findById(id);
	}
  	
  	@Override
  	public ResponseEntity<Client> assignAdvisorToClient(Long clientId, Long advisorId) throws AssignAdvisorToClientException {
  		Client client = repository.findById(clientId).orElseThrow(() -> new AssignAdvisorToClientException("Erreur, client inconnu"));
  		Advisor advisor = advisorRepository.findById(advisorId).orElseThrow(() -> new AssignAdvisorToClientException("Erreur, conseiller inconnu"));
  		
  		client.setAdvisor(advisor);
  		repository.save(client);
		return ResponseEntity.ok(client);
  	}

}
