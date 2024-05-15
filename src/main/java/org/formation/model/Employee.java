package org.formation.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "Employee")
public class Employee extends Person {
	public Employee() {
		super();
	}

}
