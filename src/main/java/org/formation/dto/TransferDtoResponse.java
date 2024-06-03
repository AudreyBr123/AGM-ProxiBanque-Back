package org.formation.dto;

public class TransferDtoResponse {

	double amount;

	public TransferDtoResponse() {
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
