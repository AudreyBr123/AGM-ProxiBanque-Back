package org.formation.model;

public class Client extends Person {
	private CurrentAccount currentAccount;
	private SavingAccount savingAccount;
	
	public Client() {
		super();
	}
	
	public Client(String firstName, String lastName, String email, String phoneNumber, Address address) {
		super(firstName, lastName, email, phoneNumber, address);
	}

	public Client(CurrentAccount currentAccount, SavingAccount savingAccount) {
		super();
		this.currentAccount = currentAccount;
		this.savingAccount = savingAccount;
	}

	public CurrentAccount getCurrentAccount() {
		return currentAccount;
	}

	public void setCurrentAccount(CurrentAccount currentAccount) {
		this.currentAccount = currentAccount;
	}

	public SavingAccount getSavingAccount() {
		return savingAccount;
	}

	public void setSavingAccount(SavingAccount savingAccount) {
		this.savingAccount = savingAccount;
	}

}
