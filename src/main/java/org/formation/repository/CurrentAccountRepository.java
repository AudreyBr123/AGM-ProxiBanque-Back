package org.formation.repository;

import org.formation.model.CurrentAccount;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * JPA Repository permettant d'interragir avec les données des comptes courants
 */
public interface CurrentAccountRepository extends JpaRepository<CurrentAccount, Long> {

}
