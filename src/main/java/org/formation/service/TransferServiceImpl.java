package org.formation.service;

import org.formation.dto.TransferDtoRequest;
import org.formation.dto.TransferDtoResponse;
import org.formation.model.Client;
import org.formation.model.CurrentAccount;
import org.formation.model.SavingAccount;
import org.formation.repository.ClientRepository;
import org.formation.repository.CurrentAccountRepository;
import org.formation.repository.SavingAccountRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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


	/**
	 * Méthode publique pour executer un virement
	 */
	@Override
	public ResponseEntity<TransferDtoResponse> executeTransfer(TransferDtoRequest transfer)
			throws NullPointerException, TransferException {
		if (transfer.getTypeCreditAccount().equals("currentAccount")
				&& transfer.getTypeDebitAccount().equals("savingAccount")) {
			return initiateTransferFromSavingToCurrent(transfer);
		}
		if (transfer.getTypeCreditAccount().equals("currentAccount")
				&& transfer.getTypeDebitAccount().equals("currentAccount")) {
			return initiateTransferFromCurrentToCurrent(transfer);
		}
		if (transfer.getTypeCreditAccount().equals("savingAccount")
				&& transfer.getTypeDebitAccount().equals("currentAccount")) {
			return initiateTransferFromCurrentToSaving(transfer);
		}
		return ResponseEntity.badRequest().build();
	}

	/**
	 * Méthode privée pour executer un virement d'un compte épargne vers un compte
	 * courant. Elle n'est exécutable qu'entre les comptes d'un client
	 */
	private ResponseEntity<TransferDtoResponse> initiateTransferFromSavingToCurrent(TransferDtoRequest transfer)
			throws NullPointerException, TransferException {
		
		// Trouve le compte de crédit (courant)
		CurrentAccount creditAccount = currentAccountRepository.findById(transfer.getIdCreditAccount())
				.orElseThrow(() -> new NullPointerException());

		// Trouve le compte de débit (épargne)
		SavingAccount debitAccount = savingAccountRepository.findById(transfer.getIdDebitAccount())
				.orElseThrow(() -> new NullPointerException());

		// Trouve les clients associés aux comptes
		Client clientCreditAccount = clientRepository.findByCurrentAccount(creditAccount);
		Client clientDebitAccount = clientRepository.findBySavingAccount(debitAccount);

		// Si le virement est entre les comptes de clients différents, lancer une
		// exception
		if (clientCreditAccount != clientDebitAccount) {
			throw new TransferException(
					"Erreur, il n'est pas possible de faire un virement d'un compte épargne au compte courant d'un autre client");
		}

		// Si on arrive ici, aucune exception n'a été soulevée : on fait le virement
		LOG.debug("Solde du compte à créditer : " + creditAccount.getBalance() + " appartenant au client d'id "
				+ clientCreditAccount.getId() + "\n Solde du compte à débiter : " + debitAccount.getBalance()
				+ " appartenant au client d'id " + clientDebitAccount.getId() + "\n Montant : " + transfer.getAmount());
		debitAccount.debitAmount(transfer.getAmount());
		creditAccount.creditAmount(transfer.getAmount());

		// Persister les deux
		currentAccountRepository.save(creditAccount);
		savingAccountRepository.save(debitAccount);
		LOG.debug("Nouveaux montants persistés");

		LOG.debug("Virement effectué, nouveau solde du compte crédité : " + creditAccount.getBalance()
				+ "\n Nouveau solde du compte débité : " + debitAccount.getBalance());

		TransferDtoResponse transferDtoResponse = new TransferDtoResponse(transfer.getAmount());
		return ResponseEntity.ok().body(transferDtoResponse);
	}

	/**
	 * Méthode privée pour executer un virement d'un compte courant vers un compte
	 * courant. Elle est executable tant que les comptes existent (possible de faire
	 * un virement du compte courant d'un client au compte courant d'un autre
	 * client)
	 */
	private ResponseEntity<TransferDtoResponse> initiateTransferFromCurrentToCurrent(TransferDtoRequest transfer)
			throws NullPointerException {
		// trouve le compte de crédit (courant)
		CurrentAccount creditAccount = currentAccountRepository.findById(transfer.getIdCreditAccount())
				.orElseThrow(() -> new NullPointerException());

		// trouve le compte de débit (courant)
		CurrentAccount debitAccount = currentAccountRepository.findById(transfer.getIdDebitAccount())
				.orElseThrow(() -> new NullPointerException());

		// Executer le virement
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

		TransferDtoResponse transferDtoResponse = new TransferDtoResponse(transfer.getAmount());
		return ResponseEntity.ok().body(transferDtoResponse);

	}

	/**
	 * Méthode privée pour executer un virement d'un compte courant vers un compte
	 * épargne. Elle n'est exécutable qu'entre les comptes d'un client
	 */
	private ResponseEntity<TransferDtoResponse> initiateTransferFromCurrentToSaving(TransferDtoRequest transfer)
			throws NullPointerException, TransferException {
		// trouve le compte de crédit (courant)
		SavingAccount creditAccount = savingAccountRepository.findById(transfer.getIdCreditAccount()).orElseThrow(() -> new NullPointerException());

		// trouve le compte de débit (épargne)
		CurrentAccount debitAccount = currentAccountRepository.findById(transfer.getIdDebitAccount()).orElseThrow(() -> new NullPointerException());

		// trouve les clients associés aux comptes
		Client clientCreditAccount = clientRepository.findBySavingAccount(creditAccount);
		Client clientDebitAccount = clientRepository.findByCurrentAccount(debitAccount);

		// si les deux ne sont pas null et que le virement est entre les comptes d'un
		// même client, executer le virement
		if (clientCreditAccount != clientDebitAccount) {
			throw new TransferException(
					"Erreur, il n'est pas possible de faire un virement d'un compte courant au compte épargne d'un autre client");
		}

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

			TransferDtoResponse transferDtoResponse = new TransferDtoResponse(transfer.getAmount());
			return ResponseEntity.ok().body(transferDtoResponse);		

	}

	// Impossible de faire un virement d'un compte épargne vers un autre compte
	// épargne

}
