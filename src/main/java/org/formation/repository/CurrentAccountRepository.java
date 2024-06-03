package org.formation.repository;

import org.formation.model.CurrentAccount;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CurrentAccountRepository extends JpaRepository<CurrentAccount, Long> {

}
