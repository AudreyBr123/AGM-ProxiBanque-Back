package org.formation.model;

import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Advisor {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Embedded
	private PersonInfos personInfos;
	
	public Advisor() {
	}

	public Advisor(PersonInfos personInfos) {
		this.personInfos = personInfos;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public PersonInfos getPersonInfos() {
		return personInfos;
	}

	public void setPersonInfos(PersonInfos personInfos) {
		this.personInfos = personInfos;
	}
	
	
}
