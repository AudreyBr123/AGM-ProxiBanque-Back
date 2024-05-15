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
		super();
	}

	public Advisor(PersonInfos personInfos) {
		this.personInfos = personInfos;
	}
}
