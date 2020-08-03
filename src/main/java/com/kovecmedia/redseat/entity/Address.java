package com.kovecmedia.redseat.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.kovecmedia.redseat.model.AddressStatus;

@Entity
@Table(name = "addresses")
public class Address {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String addressline1;

	private String addressline2;

	private String zipcode;

	@Column(name = "created_at", insertable = false, updatable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")

	private java.sql.Timestamp created_at;

	@Column(name = "Update_at", insertable = false, updatable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")

	private java.sql.Timestamp Update_at;

	private String Update_by;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "country_id")
	private Country country;

	@Column(length = 8)
	private AddressStatus type;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public String getZipcode() {
		return zipcode;
	}

	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}

	public Country getCountry() {
		return country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}

	public AddressStatus getType() {
		return type;
	}

	public void setType(AddressStatus type) {
		this.type = type;
	}

	public java.sql.Timestamp getCreated_at() {
		return created_at;
	}

	public void setCreated_at(java.sql.Timestamp created_at) {
		this.created_at = created_at;
	}

	public java.sql.Timestamp getUpdate_at() {
		return Update_at;
	}

	public void setUpdate_at(java.sql.Timestamp update_at) {
		Update_at = update_at;
	}

	public String getUpdate_by() {
		return Update_by;
	}

	public void setUpdate_by(String update_by) {
		Update_by = update_by;
	}

	@Override
	public String toString() {
		return "Address [id=" + id + ", addressline1=" + addressline1 + ", addressline2=" + addressline2 + ", zipcode="
				+ zipcode + ", created_at=" + created_at + ", Update_at=" + Update_at + ", Update_by=" + Update_by
				+ ", country=" + country + ", type=" + type + "]";
	}

	
	
}
