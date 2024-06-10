package org.formation.init;

import org.formation.exception.AssignAdvisorToClientException;
import org.formation.model.Advisor;
import org.formation.model.Client;
import org.formation.model.CurrentAccount;
import org.formation.model.PersonInfos;
import org.formation.model.SavingAccount;
import org.formation.service.AdvisorService;
import org.formation.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;

/**
 * Cette classe initialise un jeu de données
 */
@Component
public class ReInitDefaultDataSet {
	@Autowired
	ClientService clientService;
	
	@Autowired
	AdvisorService advisorService;
	
	@PostConstruct
	public void dataInit() throws AssignAdvisorToClientException {
		System.out.println("Initialisation d'un jeu de données");
		
		// Clients (avec comptes)
		PersonInfos firstInfos = new PersonInfos("Audrey",	"Boureau", "audrey.boureau@proxibanque.fr", "0606060606", "rue de SpringBoot", "44000", "Nantes");
		CurrentAccount firstCurrentAccount = new CurrentAccount(520.7);
		SavingAccount firstSavingAccount = new SavingAccount(3111.5);
		
		PersonInfos secondInfos = new PersonInfos("Gwendal",	"Breton", "gwendal.breton@proxibanque.fr", "0607070707", "avenue du Back End", "25870", "Geneuille");
		CurrentAccount secondCurrentAccount = new CurrentAccount(403);
		SavingAccount secondSavingAccount = new SavingAccount(2088.1);
		
		PersonInfos thirdInfos = new PersonInfos("Marine",	"Spaak", "marine.spaak@proxibanque.fr", "0608080808", "place Angular", "44000", "Nantes");
		CurrentAccount thirdCurrentAccount = new CurrentAccount(655.5);
		SavingAccount thirdSavingAccount = new SavingAccount(1000);
		
		PersonInfos fourthInfos = new PersonInfos("Alexane",	"Durozier", "alexane.durozier@dev.fr", "0608080808", "rue du Pilou-pilou", "31000", "Toulouse");
		CurrentAccount fourthCurrentAccount = new CurrentAccount(103);
		SavingAccount fourthSavingAccount = new SavingAccount(3800);
		
		PersonInfos fifthInfos = new PersonInfos("Chloé",	"Sirgent", "cloclo@msn.com", "0608080808", "rue de la Soif", "44000", "Nantes");
		CurrentAccount fifthCurrentAccount = new CurrentAccount(103);
		SavingAccount fifthSavingAccount = new SavingAccount(3800);
		
		PersonInfos sixthInfos = new PersonInfos("Morgan",	"Durozier", "momo.durozier@dev.fr", "0608080808", "avenue de la Gymnastique", "75000", "Paris");
		CurrentAccount sixthCurrentAccount = new CurrentAccount(903);
		SavingAccount sixthSavingAccount = new SavingAccount(3200);
		
		PersonInfos seventhInfos = new PersonInfos("Elias",	"Asri", "elias.asri@motocross.com", "0608080808", "boulevard de la Vitesse", "31000", "Toulouse");
		CurrentAccount seventhCurrentAccount = new CurrentAccount(93.8);
		SavingAccount seventhSavingAccount = new SavingAccount(4150);
		
		PersonInfos eigthInfos = new PersonInfos("Marine",	"Delanoe", "marine.delanoe@dev.fr", "0608080808", "rue de l'Humour", "49000", "Angers");
		CurrentAccount eigthCurrentAccount = new CurrentAccount(505.5);
		SavingAccount eigthSavingAccount = new SavingAccount(2505);
		
		PersonInfos ninthInfos = new PersonInfos("Ameline",	"Houis", "ameline.houis@capgemini.fr", "0608080808", "rue du Chaton", "44000", "Nantes");
		CurrentAccount ninthCurrentAccount = new CurrentAccount(109);
		SavingAccount ninthSavingAccount = new SavingAccount(998);
		
		Client firstClient = new Client(firstInfos, firstCurrentAccount, firstSavingAccount);
		Client secondClient = new Client(secondInfos, secondCurrentAccount, secondSavingAccount);
		Client thirdClient = new Client(thirdInfos, thirdCurrentAccount, thirdSavingAccount);
		Client fourthClient = new Client(fourthInfos, fourthCurrentAccount, fourthSavingAccount);
		Client fifthClient = new Client(fifthInfos, fifthCurrentAccount, fifthSavingAccount);
		Client sixthClient = new Client(sixthInfos, sixthCurrentAccount, sixthSavingAccount);
		Client seventhClient = new Client(seventhInfos, seventhCurrentAccount, seventhSavingAccount);
		Client eigthClient = new Client(eigthInfos, eigthCurrentAccount, eigthSavingAccount);
		Client ninthClient = new Client(ninthInfos, ninthCurrentAccount, ninthSavingAccount);

		clientService.save(firstClient);
		clientService.save(secondClient);
		clientService.save(thirdClient);
		clientService.save(fourthClient);
		clientService.save(fifthClient);
		clientService.save(sixthClient);
		clientService.save(seventhClient);
		clientService.save(eigthClient);
		clientService.save(ninthClient);

		// Conseillers
		PersonInfos firstAdvisorInfos = new PersonInfos("Muriel",	"Lebourdais", "m.lebourdais@m2i.fr", "0606060606", "rue de la Formation", "75000", "Paris");
		PersonInfos secondAdvisorInfos = new PersonInfos("Yann",	"Creach", "y.creach@m2i.fr", "0607080910", "boulevard de la Pédagogie", "75000", "Paris");

		Advisor firstAdvisor = new Advisor(firstAdvisorInfos);
		Advisor secondAdvisor = new Advisor(secondAdvisorInfos);
		
		advisorService.save(firstAdvisor);
		advisorService.save(secondAdvisor);
		
		// Affectation des clients aux conseillers
		clientService.assignAdvisorToClient(firstClient.getId(), secondAdvisor.getId());
		clientService.assignAdvisorToClient(secondClient.getId(), secondAdvisor.getId());
		clientService.assignAdvisorToClient(thirdClient.getId(), secondAdvisor.getId());
		clientService.assignAdvisorToClient(fourthClient.getId(), secondAdvisor.getId());
		clientService.assignAdvisorToClient(fifthClient.getId(), secondAdvisor.getId());
		clientService.assignAdvisorToClient(sixthClient.getId(), firstAdvisor.getId());
		clientService.assignAdvisorToClient(seventhClient.getId(), firstAdvisor.getId());
		clientService.assignAdvisorToClient(eigthClient.getId(), firstAdvisor.getId());
		clientService.assignAdvisorToClient(ninthClient.getId(), firstAdvisor.getId());

	}

}
