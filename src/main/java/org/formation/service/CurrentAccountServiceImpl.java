package org.formation.service;

import java.util.List;
import java.util.Optional;

import org.formation.model.CurrentAccount;
import org.formation.repository.CurrentAccountRepository;
import org.springframework.stereotype.Service;

@Service
public class CurrentAccountServiceImpl implements CurrentAccountService {

	CurrentAccountRepository repository;

	public CurrentAccountServiceImpl(CurrentAccountRepository repository) {
		this.repository = repository;
	}

	@Override
	public List<CurrentAccount> getAll() {
		return repository.findAll();
	}

	@Override
	public Optional<CurrentAccount> getById(Long id) {
		return repository.findById(id);
	}

	@Override
	public CurrentAccount save(CurrentAccount currentAccount) {
		return repository.save(currentAccount);
	}

}
