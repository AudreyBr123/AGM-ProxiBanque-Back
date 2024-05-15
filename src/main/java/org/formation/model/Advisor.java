package org.formation.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "Advisor")
public class Advisor extends Employee {
	public Advisor() {
		super();
	}

}
