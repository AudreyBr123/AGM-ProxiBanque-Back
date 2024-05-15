package org.formation.model;

import jakarta.persistence.Embeddable;

@Embeddable
public class CurrentAccount extends Account {
	public CurrentAccount() {
		super();
	}

	public CurrentAccount(double balance) {
		super(balance);
	}

}
