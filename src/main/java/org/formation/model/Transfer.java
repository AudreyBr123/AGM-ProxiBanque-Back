package org.formation.model;

// DTO 
public class Transfer {
	//Strings : types d'account from et to
	Long idDebitAccount;
	Long idCreditAccount;
	//ajouter validator not negative
	double amount;

	public Transfer() {
	}

	public Transfer(Long idDebitAccount, Long idCreditAccount, double amount) {
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

	@Override
	public String toString() {
		return "Transfer [idDebitAccount=" + idDebitAccount + ", idCreditAccount=" + idCreditAccount + ", amount="
				+ amount + "]";
	}
	

}
