package org.formation.repository;

import org.formation.model.Client;
import org.formation.model.CurrentAccount;
import org.formation.model.SavingAccount;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * JPA Repository permettant d'interragir avec les données des clients
 */
public interface ClientRepository extends JpaRepository<Client, Long> {

	/**
	 * Méthode pour retrouver un client à partir de son compte courant
	 */
	Client findByCurrentAccount(CurrentAccount currentAccount);

	/**
	 * Méthode pour retrouver un client à partir de son compte épargne
	 */
	Client findBySavingAccount(SavingAccount savingAccount);
}
