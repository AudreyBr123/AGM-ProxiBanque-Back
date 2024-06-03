package org.formation.model;

import java.time.LocalDate;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.Min;

@Entity
public class CurrentAccount {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Min(value = 0, message="Balance cannot be lower than 0")
	private double balance;
	private LocalDate creationDate;

	@OneToOne(mappedBy = "currentAccount")
	@JsonBackReference
	private Client client;

	public CurrentAccount() {
		this.creationDate = LocalDate.now();
	}

	 public CurrentAccount(@Min(value = 0, message = "Balance cannot be lower than 0") double balance) {
	 	this.balance = balance;
	 	this.creationDate = LocalDate.now();
	 }

//	public CurrentAccount(@Min(value = 0, message = "Balance cannot be lower than 0") double balance,
//			LocalDate creationDate) {
//		this.balance = balance;
//		this.creationDate = creationDate;
//	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public LocalDate getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(LocalDate creationDate) {
		this.creationDate = creationDate;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	@Override
	public String toString() {
		return "Account [id=" + id + ", balance=" + balance + ", creationDate=" + creationDate + "]";
	}
	
	
	//METHODES
	public void creditAmount(double amount) {
		setBalance(getBalance() + amount);
	}
	
	public void debitAmount(double amount) {
		setBalance(getBalance() - amount);
	}
}
