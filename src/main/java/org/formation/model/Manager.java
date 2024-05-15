package org.formation.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "Manager")
public class Manager extends Employee {
	public Manager() {
		super();
	}

}
