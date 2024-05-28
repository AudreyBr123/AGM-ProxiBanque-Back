package org.formation.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.formation.model.Client;
import org.formation.model.CurrentAccount;
import org.formation.model.PersonInfos;
import org.formation.model.SavingAccount;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class TestClientRepository {

	private static Logger logger = LoggerFactory.getLogger(TestClientRepository.class);

	@Autowired
	private ClientRepository clientRepository;


	@Test
	public void findByCurrentAccountShouldReturnClient() {
		CurrentAccount currentAccountA = new CurrentAccount(100.0);
		SavingAccount savingAccountA = new SavingAccount(500.0);
		Client clientA = new Client(
				new PersonInfos("Jean", "Pierre", "j.p@gmail.com", "0606666", "rue de la paix", "789788", "Paris"),
				currentAccountA, savingAccountA);
		clientRepository.save(clientA);
		
		logger.debug("Le client a : "+clientA.getPersonInfos().getFirstName());
		Client clientFound = clientRepository.findByCurrentAccount(currentAccountA);
		logger.debug("Le client trouve : "+clientFound.getPersonInfos().getFirstName());

		assertEquals(clientA.getPersonInfos().getFirstName(), clientFound.getPersonInfos().getFirstName());
		
		
	}
}
