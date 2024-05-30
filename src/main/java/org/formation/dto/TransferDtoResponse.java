package org.formation.dto;

public class TransferDtoResponse {

	// Reponse avec infos du compte crediteur uniquement ?
	// pas censé voir les infos du compte debiteur
	double amount;

	public TransferDtoResponse() {
		// TODO Auto-generated constructor stub
	}

	public TransferDtoResponse(double amount) {
		this.amount = amount;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

}
