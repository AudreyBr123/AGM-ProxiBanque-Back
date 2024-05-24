package org.formation.service;

import org.formation.model.CurrentAccount;
import org.formation.model.SavingAccount;
import org.formation.model.Transfer;
import org.formation.repository.CurrentAccountRepository;
import org.formation.repository.SavingAccountRepository;
import org.springframework.stereotype.Service;

@Service
public class TransferServiceImpl implements TransferService {

	CurrentAccountRepository currentAccountRepository;
	SavingAccountRepository savingAccountRepository;

	public TransferServiceImpl(CurrentAccountRepository currentAccountRepository,
			SavingAccountRepository savingAccountRepository) {
		this.currentAccountRepository = currentAccountRepository;
		this.savingAccountRepository = savingAccountRepository;
	}

	// TO DO : créer un DTO_response avec ce qu'on veut renvoyer en réponse à un
	// transfert (type de compte, id, montant etc.)
	// Transformer le type de retour des fonctions privées en response
	// entity<DTO_response>, et celui d'executeTransfer aussi
	// Transformer le type de retour dans le controleur

	/**
	 * Méthode publique pour executer un virement
	 */
	@Override
	public void executeTransfer(Transfer transfer) {
		if (transfer.getTypeCreditAccount().equals("currentAccount")
				&& transfer.getTypeDebitAccount().equals("savingAccount")) {
			initiateTransferFromSavingToCurrent(transfer);
		}
		if (transfer.getTypeCreditAccount().equals("currentAccount")
				&& transfer.getTypeDebitAccount().equals("currentAccount")) {
			initiateTransferFromCurrentToCurrent(transfer);
		}
		if (transfer.getTypeCreditAccount().equals("savingAccount")
				&& transfer.getTypeDebitAccount().equals("currentAccount")) {
			initiateTransferFromCurrentToSaving(transfer);
		}
	}

	/**
	 * Méthode privée pour executer un virement d'un compte épargne vers un compte
	 * courant
	 */
	private void initiateTransferFromSavingToCurrent(Transfer transfer) {
		// trouve le compte de crédit (courant)
		CurrentAccount creditAccount = currentAccountRepository.findById(transfer.getIdCreditAccount()).orElse(null);

		// trouve le compte de débit (épargne)
		SavingAccount debitAccount = savingAccountRepository.findById(transfer.getIdDebitAccount()).orElse(null);

		// si les deux ne sont pas null, executer le virement
		if (debitAccount != null && creditAccount != null) {
			debitAccount.debitAmount(transfer.getAmount());
			creditAccount.creditAmount(transfer.getAmount());

			// persister les deux
			currentAccountRepository.save(creditAccount);
			savingAccountRepository.save(debitAccount);
		}

	}

	/**
	 * Méthode privée pour executer un virement d'un compte courant vers un compte
	 * courant
	 */
	private void initiateTransferFromCurrentToCurrent(Transfer transfer) {
		// trouve le compte de crédit (courant)
		CurrentAccount creditAccount = currentAccountRepository.findById(transfer.getIdCreditAccount()).orElse(null);

		// trouve le compte de débit (courant)
		CurrentAccount debitAccount = currentAccountRepository.findById(transfer.getIdDebitAccount()).orElse(null);

		// si les deux ne sont pas null, executer le virement
		if (debitAccount != null && creditAccount != null) {
			debitAccount.debitAmount(transfer.getAmount());
			creditAccount.creditAmount(transfer.getAmount());
		}

		// persister les deux
		currentAccountRepository.save(creditAccount);
		currentAccountRepository.save(debitAccount);
	}

	/**
	 * Méthode privée pour executer un virement d'un compte courant vers un compte
	 * épargne
	 */
	private void initiateTransferFromCurrentToSaving(Transfer transfer) {
		// trouve le compte de crédit (courant)
		SavingAccount creditAccount = savingAccountRepository.findById(transfer.getIdCreditAccount()).orElse(null);

		// trouve le compte de débit (épargne)
		CurrentAccount debitAccount = currentAccountRepository.findById(transfer.getIdDebitAccount()).orElse(null);

		// si les deux ne sont pas null, executer le virement
		if (debitAccount != null && creditAccount != null) {
			debitAccount.debitAmount(transfer.getAmount());
			creditAccount.creditAmount(transfer.getAmount());
		}

		// persister les deux
		savingAccountRepository.save(creditAccount);
		currentAccountRepository.save(debitAccount);
	}

	// Impossible de faire un virement d'un compte épargne vers un autre compte
	// épargne

}
