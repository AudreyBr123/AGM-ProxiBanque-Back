package org.formation.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.validation.Valid;

@Entity
public class Client {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Valid
	@Embedded
	private PersonInfos personInfos;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "currentAccount_id", unique = true)
	private CurrentAccount currentAccount;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "savingAccount_id", unique = true)
	private SavingAccount savingAccount;
	
	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name="advisor_id")
	@JsonBackReference
	private Advisor advisor;

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
	
	public Advisor getAdvisor() {
		return advisor;
	}

	public void setAdvisor(Advisor advisor) {
		this.advisor = advisor;
	}

	@Override
	public String toString() {
		return "Client [id=" + id + ", personInfos=" + personInfos + ", currentAccount=" + currentAccount
				+ ", savingAccount=" + savingAccount + ", advisor=" + advisor + "]";
	}

}
