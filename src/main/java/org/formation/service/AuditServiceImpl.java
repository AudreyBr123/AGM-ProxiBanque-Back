package org.formation.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.formation.model.CurrentAccount;
import org.formation.repository.CurrentAccountRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class AuditServiceImpl implements AuditService {
	private static Logger LOG = LoggerFactory.getLogger(TransferServiceImpl.class);

	@Autowired
	CurrentAccountRepository currentAccountRepository;

	@Override
	public List<Double> getAllBalances() {
		List<Double> balances = new ArrayList<>();
		List<CurrentAccount> accounts = currentAccountRepository.findAll();
		balances = accounts.stream().map(CurrentAccount::getBalance).toList();
		return balances;
	}

	@Override
	public Map<Long, Double> getCriticalBalances() {
		List<Double> balancesOnly = new ArrayList<>();
		Map<Long, Double> balances = new HashMap<>();
		List<CurrentAccount> accounts = currentAccountRepository.findByBalanceLessThan(10.0);
		//LOG.debug("comptes" + accounts);
		//balancesOnly = accounts.stream().map(CurrentAccount::getBalance).toList();
		
		balances = accounts.stream().collect(Collectors.toMap(CurrentAccount::getId, CurrentAccount::getBalance));
		LOG.debug("balances" + balances);

		return balances;
	}

}
