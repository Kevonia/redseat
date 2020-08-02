package com.kovecmedia.redseat.payload.request;

import java.util.Set;

public class UserRegistration {
	private String name;
	private String password;
	private String passwordConfirmation;
	private String phone;

	private String email;

	private String addressline1;

	private String addressline2;

	private String zipcode;
	
	 private Set<String> role;

	public UserRegistration() {
	}

	public UserRegistration(String username, String password, String passwordConfirmation, String phone,
			String addressline1, String addressline2, String zipcode, String email) {
		super();
		this.name = username;
		this.password = password;
		this.passwordConfirmation = passwordConfirmation;
		this.phone = phone;
		this.addressline1 = addressline1;
		this.addressline2 = addressline2;
		this.zipcode = zipcode;
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String username) {
		this.name = username;
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

	public String getAddressline1() {
		return addressline1;
	}

	public void setAddressline1(String addressline1) {
		this.addressline1 = addressline1;
	}

	public String getAddressline2() {
		return addressline2;
	}

	public void setAddressline2(String addressline2) {
		this.addressline2 = addressline2;
	}

	public String getZipCode() {
		return zipcode;
	}

	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
    public Set<String> getRole() {
        return this.role;
      }
      
      public void setRole(Set<String> role) {
        this.role = role;
      }

}
