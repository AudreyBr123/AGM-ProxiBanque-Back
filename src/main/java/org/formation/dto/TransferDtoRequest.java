package org.formation.dto;

import jakarta.validation.constraints.Positive;

public class TransferDtoRequest {

	// types de comptes (peuvent être courant ou épargne : validateur possible?)
	String typeCreditAccount;
	String typeDebitAccount;

	// id des comptes
	Long idCreditAccount;
	Long idDebitAccount;

	@Positive
	double amount;

	public TransferDtoRequest() {
	}

	public TransferDtoRequest(String typeDebitAccount, String typeCreditAccount, Long idDebitAccount, Long idCreditAccount,
			double amount) {
		this.typeDebitAccount = typeDebitAccount;
		this.typeCreditAccount = typeCreditAccount;
		this.idDebitAccount = idDebitAccount;
		this.idCreditAccount = idCreditAccount;
		this.amount = amount;
	}

	public Long getIdDebitAccount() {
		return idDebitAccount;
	}

	public void setIdDebitAccount(Long idDebitAccount) {
		this.idDebitAccount = idDebitAccount;
	}

	public Long getIdCreditAccount() {
		return idCreditAccount;
	}

	public void setIdCreditAccount(Long idCreditAccount) {
		this.idCreditAccount = idCreditAccount;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public String getTypeDebitAccount() {
		return typeDebitAccount;
	}

	public void setTypeDebitAccount(String typeDebitAccount) {
		this.typeDebitAccount = typeDebitAccount;
	}

	public String getTypeCreditAccount() {
		return typeCreditAccount;
	}

	public void setTypeCreditAccount(String typeCreditAccount) {
		this.typeCreditAccount = typeCreditAccount;
	}

	@Override
	public String toString() {
		return "Transfer [idDebitAccount=" + idDebitAccount + ", idCreditAccount=" + idCreditAccount + ", amount="
				+ amount + "]";
	}

}
