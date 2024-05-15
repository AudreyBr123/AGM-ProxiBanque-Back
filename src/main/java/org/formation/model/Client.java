package org.formation.model;

public class Client extends Person {
	private CurrentAccount currentAccount;
	private SavingAccount savingAccount;
	
	public Client() {
		super();
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
