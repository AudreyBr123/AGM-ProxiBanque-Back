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

	public void executeTransfer(Transfer transfer) {

		// trouve le compte de crédit (courant)
		if (transfer.getTypeCreditAccount().equals("currentAccount")) {
			CurrentAccount creditAccount = currentAccountRepository.findById(transfer.getIdCreditAccount())
					.orElse(null);

			// trouve le compte de débit (épargne)
			if (transfer.getTypeDebitAccount().equals("savingAccount")) {
				SavingAccount debitAccount = savingAccountRepository.findById(transfer.getIdDebitAccount())
						.orElse(null);

				// si les deux ne sont pas null, executer le virement
				if (debitAccount != null && creditAccount != null) {
					debitAccount.debitAmount(transfer.getAmount());
					creditAccount.creditAmount(transfer.getAmount());
				}

				// persister les deux
				currentAccountRepository.save(creditAccount);
				savingAccountRepository.save(debitAccount);
			}
		}

	}

}
