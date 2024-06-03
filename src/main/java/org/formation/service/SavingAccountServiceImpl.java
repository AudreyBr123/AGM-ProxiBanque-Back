package org.formation.service;

import java.util.List;

import org.formation.model.SavingAccount;
import org.formation.repository.SavingAccountRepository;
import org.springframework.stereotype.Service;

@Service
public class SavingAccountServiceImpl implements SavingAccountService {

	SavingAccountRepository repository;

	public SavingAccountServiceImpl(SavingAccountRepository repository) {
		this.repository = repository;
	}

	@Override
	public List<SavingAccount> getAll() {
		return repository.findAll();
	}


}
