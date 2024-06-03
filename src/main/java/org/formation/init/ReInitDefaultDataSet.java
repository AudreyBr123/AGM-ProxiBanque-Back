package org.formation.init;

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

@Component
public class ReInitDefaultDataSet {
	@Autowired
	ClientService clientService;
	
	@Autowired
	AdvisorService advisorService;
	
	@PostConstruct
	public void dataInit() {
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
		
		Client firstClient = new Client(firstInfos, firstCurrentAccount, firstSavingAccount);
		Client secondClient = new Client(secondInfos, secondCurrentAccount, secondSavingAccount);
		Client thirdClient = new Client(thirdInfos, thirdCurrentAccount, thirdSavingAccount);

		clientService.save(firstClient);
		clientService.save(secondClient);
		clientService.save(thirdClient);
	
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
		clientService.assignAdvisorToClient(thirdClient.getId(), firstAdvisor.getId());
	}

}
