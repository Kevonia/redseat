package com.kovecmedia.redseat.payload.request;

import java.util.Set;

public class UserRegistration {
	private String name;
	private String password;
	private String passwordConfirmation;
	private String phone;

	private String email;

	private String addressLine1;

	private String addressLine2;

	private String zipCode;
	
	private Set<String> role;
	
	private long refCode;

	public long getRefCode() {
		return refCode;
	}

	public void setRefCode(long refCode) {
		this.refCode = refCode;
	}

	public UserRegistration() {
		
	}

	public UserRegistration(String username, String password, String passwordConfirmation, String phone,
			String addressline1, String addressline2, String zipcode, String email) {
		super();
		this.name = username;
		this.password = password;
		this.passwordConfirmation = passwordConfirmation;
		this.phone = phone;
		this.addressLine1 = addressline1;
		this.addressLine2 = addressline2;
		this.zipCode = zipcode;
		this.email = email;
		
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPasswordConfirmation() {
		return passwordConfirmation;
	}

	public void setPasswordConfirmation(String passwordConfirmation) {
		this.passwordConfirmation = passwordConfirmation;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddressLine1() {
		return addressLine1;
	}

	public void setAddressLine1(String addressLine1) {
		this.addressLine1 = addressLine1;
	}

	public String getAddressLine2() {
		return addressLine2;
	}

	public void setAddressLine2(String addressLine2) {
		this.addressLine2 = addressLine2;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public Set<String> getRole() {
		return role;
	}



	
	


}
