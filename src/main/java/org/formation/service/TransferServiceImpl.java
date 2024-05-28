package org.formation.service;

import org.formation.model.Client;
import org.formation.model.CurrentAccount;
import org.formation.model.SavingAccount;
import org.formation.model.Transfer;
import org.formation.repository.ClientRepository;
import org.formation.repository.CurrentAccountRepository;
import org.formation.repository.SavingAccountRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class TransferServiceImpl implements TransferService {

	private static Logger LOG = LoggerFactory.getLogger(TransferServiceImpl.class);

	@Autowired
	CurrentAccountRepository currentAccountRepository;

	@Autowired
	SavingAccountRepository savingAccountRepository;

	@Autowired
	ClientRepository clientRepository;

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
	 * courant. Elle n'est exécutable qu'entre les comptes d'un client
	 */
	private void initiateTransferFromSavingToCurrent(Transfer transfer) {
		// trouve le compte de crédit (courant)
		CurrentAccount creditAccount = currentAccountRepository.findById(transfer.getIdCreditAccount()).orElse(null);

		// trouve le compte de débit (épargne)
		SavingAccount debitAccount = savingAccountRepository.findById(transfer.getIdDebitAccount()).orElse(null);

		// trouve les clients associés aux comptes
		Client clientCreditAccount = clientRepository.findByCurrentAccount(creditAccount);
		Client clientDebitAccount = clientRepository.findBySavingAccount(debitAccount);

		// si les deux ne sont pas null et que le virement est entre les comptes d'un
		// même client, executer le virement
		if (debitAccount != null && creditAccount != null && (clientCreditAccount == clientDebitAccount)) {

			LOG.debug("Solde du compte à créditer : " + creditAccount.getBalance() + " appartenant au client d'id "
					+ clientCreditAccount.getId() + "\n Solde du compte à débiter : " + debitAccount.getBalance()
					+ " appartenant au client d'id " + clientDebitAccount.getId() + "\n Montant : "
					+ transfer.getAmount());
			debitAccount.debitAmount(transfer.getAmount());
			creditAccount.creditAmount(transfer.getAmount());

			// persister les deux
			currentAccountRepository.save(creditAccount);
			savingAccountRepository.save(debitAccount);
			LOG.debug("Nouveaux montants persistés");

			LOG.debug("Virement effectué, nouveau solde du compte crédité : " + creditAccount.getBalance()
					+ "\n Nouveau solde du compte débité : " + debitAccount.getBalance());
		} else {
			LOG.error(
					"Erreur, il n'est pas possible de faire un virement d'un compte épargne au compte courant d'un autre client, ou un des comptes n'existe pas");
		}

	}

	/**
	 * Méthode privée pour executer un virement d'un compte courant vers un compte
	 * courant. Elle est executable tant que les comptes existent (possible de faire
	 * un virement du compte courant d'un client au compte courant d'un autre
	 * client)
	 */
	private void initiateTransferFromCurrentToCurrent(Transfer transfer) {
		// trouve le compte de crédit (courant)
		CurrentAccount creditAccount = currentAccountRepository.findById(transfer.getIdCreditAccount()).orElse(null);

		// trouve le compte de débit (courant)
		CurrentAccount debitAccount = currentAccountRepository.findById(transfer.getIdDebitAccount()).orElse(null);

		// si les deux ne sont pas null, executer le virement
		if (debitAccount != null && creditAccount != null) {
			LOG.debug("Solde du compte à créditer : " + creditAccount.getBalance() + "\n Solde du compte à débiter : "
					+ debitAccount.getBalance() + "\n Montant : " + transfer.getAmount());

			debitAccount.debitAmount(transfer.getAmount());
			creditAccount.creditAmount(transfer.getAmount());
			LOG.debug("Virement effectué");

			// persister les deux
			currentAccountRepository.save(creditAccount);
			currentAccountRepository.save(debitAccount);
			LOG.debug("Nouveaux montants persistés");

			LOG.debug("Virement effectué, nouveau solde du compte crédité : " + creditAccount.getBalance()
					+ "\n Nouveau solde du compte débité : " + debitAccount.getBalance());
		}

	}

	/**
	 * Méthode privée pour executer un virement d'un compte courant vers un compte
	 * épargne. Elle n'est exécutable qu'entre les comptes d'un client
	 */
	private void initiateTransferFromCurrentToSaving(Transfer transfer) {
		// trouve le compte de crédit (courant)
		SavingAccount creditAccount = savingAccountRepository.findById(transfer.getIdCreditAccount()).orElse(null);

		// trouve le compte de débit (épargne)
		CurrentAccount debitAccount = currentAccountRepository.findById(transfer.getIdDebitAccount()).orElse(null);

		// trouve les clients associés aux comptes
		Client clientCreditAccount = clientRepository.findBySavingAccount(creditAccount);
		Client clientDebitAccount = clientRepository.findByCurrentAccount(debitAccount);

		// si les deux ne sont pas null et que le virement est entre les comptes d'un
		// même client, executer le virement
		if (debitAccount != null && creditAccount != null && (clientCreditAccount == clientDebitAccount)) {

			LOG.debug("Solde du compte à créditer : " + creditAccount.getBalance() + " appartenant au client d'id "
					+ clientCreditAccount.getId() + "\n Solde du compte à débiter : " + debitAccount.getBalance()
					+ " appartenant au client d'id " + clientDebitAccount.getId() + "\n Montant : "
					+ transfer.getAmount());
			
			debitAccount.debitAmount(transfer.getAmount());
			creditAccount.creditAmount(transfer.getAmount());
			LOG.debug("Virement effectué");

			// persister les deux
			savingAccountRepository.save(creditAccount);
			currentAccountRepository.save(debitAccount);
			LOG.debug("Nouveaux montants persistés");
			LOG.debug("Virement effectué, nouveau solde du compte crédité : " + creditAccount.getBalance()
			+ "\n Nouveau solde du compte débité : " + debitAccount.getBalance());

		} else {
			LOG.error(
					"Erreur, il n'est pas possible de faire un virement d'un compte courant au compte épargne d'un autre client, ou un des comptes n'existe pas");
		}

	}

	// Impossible de faire un virement d'un compte épargne vers un autre compte
	// épargne

}
