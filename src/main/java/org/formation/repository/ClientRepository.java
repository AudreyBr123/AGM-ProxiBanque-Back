package org.formation.repository;

import org.formation.model.Client;
import org.formation.model.CurrentAccount;
import org.formation.model.SavingAccount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Long> {

	// Permet de retrouver le client à partir de son compte courant ou épargne,
	// utilisé dans les virements
	Client findByCurrentAccount(CurrentAccount currentAccount);

	Client findBySavingAccount(SavingAccount savingAccount);
}
