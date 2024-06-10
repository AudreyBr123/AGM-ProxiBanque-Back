package org.formation.repository;

import org.formation.model.SavingAccount;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * JPA Repository permettant d'interragir avec les données des comptes épargnes
 */
public interface SavingAccountRepository extends JpaRepository<SavingAccount, Long> {

}
