package org.formation.service;

import java.util.List;
import java.util.Optional;

import org.formation.model.Advisor;
import org.formation.repository.AdvisorRepository;
import org.springframework.stereotype.Service;

@Service
public class AdvisorServiceImpl implements AdvisorService {

	AdvisorRepository repository;

	public AdvisorServiceImpl(AdvisorRepository repository) {
		this.repository = repository;
	}

	@Override
	public List<Advisor> getAll() {
		return repository.findAll();
	}

	@Override
	public Advisor save(Advisor advisor) {
		return repository.save(advisor);
	}

	@Override
	public Optional<Advisor> getById(Long id) {
		return repository.findById(id);
	}
}
