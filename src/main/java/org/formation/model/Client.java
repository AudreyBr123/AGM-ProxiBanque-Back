package org.formation.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;

@Entity
public class Client {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Embedded
	private PersonInfos personInfos;

	@OneToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "currentAccount_id", unique = true)
	private CurrentAccount currentAccount;

	@OneToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "savingAccount_id", unique = true)
	private SavingAccount savingAccount;

	public Client() {
	}

	public Client(PersonInfos personInfos, CurrentAccount currentAccount, SavingAccount savingAccount) {
		this.personInfos = personInfos;
		this.currentAccount = currentAccount;
		this.savingAccount = savingAccount;
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
