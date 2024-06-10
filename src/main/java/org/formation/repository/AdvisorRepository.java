package org.formation.repository;

import org.formation.model.Advisor;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * JPA Repository permettant d'interragir avec les données des conseillers
 */
public interface AdvisorRepository extends JpaRepository<Advisor, Long>{

}
