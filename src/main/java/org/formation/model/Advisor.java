package org.formation.model;

import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Advisor {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Embedded
	private PersonInfos personInfos;
	
	@OneToMany(mappedBy = "advisor", cascade = CascadeType.PERSIST)
	private Set<Client> clients = new HashSet<>();
	
	public Advisor() {
	}

	public Advisor(PersonInfos personInfos) {
		this.personInfos = personInfos;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public PersonInfos getPersonInfos() {
		return personInfos;
	}

	public void setPersonInfos(PersonInfos personInfos) {
		this.personInfos = personInfos;
	}

	public Set<Client> getClients() {
		return clients;
	}

	public void setClients(Set<Client> clients) {
		this.clients = clients;
	}
	
	public int getNumberOfClients() {
		return this.clients.size();
	}
	
}
