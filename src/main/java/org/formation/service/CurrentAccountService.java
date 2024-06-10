package org.formation.service;

import java.util.List;
import java.util.Optional;

import org.formation.model.CurrentAccount;

public interface CurrentAccountService {

	List<CurrentAccount> getAll();
	Optional<CurrentAccount> getById(Long id);
	CurrentAccount save(CurrentAccount currentAccount);
}
