package org.formation.model;

import jakarta.persistence.Embeddable;

@Embeddable
public class SavingAccount extends Account {
	public SavingAccount() {
		super();
	}

	public SavingAccount(double balance) {
		super(balance);
	}
}
