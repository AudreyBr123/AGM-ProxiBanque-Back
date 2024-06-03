package org.formation.model;

import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

@Embeddable
public class PersonInfos {
	@NotBlank(message = "first name cannot be null or blank")
    private String firstName;

	@NotBlank(message = "last name cannot be null or blank")
	private String lastName;
	
	@Email(message = "Email must be valid")
	private String email;
	
	@Pattern(regexp="(^$|[0-9]{10})", message = "Phone number must be valid")
	private String phoneNumber;
	
	@NotBlank(message = "Street cannot be null or blank")
	private String street;
	
	@Pattern(regexp="(^$|[0-9]{1,5})", message = "Zipcode number must be valid")
	private String zipCode;
	
	@NotBlank(message = "last name cannot be null or blank")
	private String city;

	public PersonInfos() {
	}

	public PersonInfos(String firstName, String lastName, String email, String phoneNumber, String street,
		String zipCode, String city) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.street = street;
		this.zipCode = zipCode;
		this.city = city;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	@Override
	public String toString() {
		return "PersonInfos [firstName=" + firstName + ", lastName=" + lastName + ", email=" + email + ", phoneNumber="
				+ phoneNumber + ", street=" + street + ", zipCode=" + zipCode + ", city=" + city + "]";
	}

	
}
