package org.formation.model;

import java.time.LocalDate;

public class Account {
	private Long id;
	private double balance;
	private LocalDate creationDate;
	
	public Account() {
	}

	public Account(double balance) {
		this.balance = balance;
		this.creationDate = LocalDate.now();
	}

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

	@Override
	public String toString() {
		return "Account [id=" + id + ", balance=" + balance + ", creationDate=" + creationDate + "]";
	}
}
