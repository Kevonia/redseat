package com.kovecmedia.redseat.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.kovecmedia.redseat.model.PaymentMethod;

@Entity
@Table(name = "Deliveries")
public class Delivery {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private Long charge;

	private String Instructions;

	private PaymentMethod paymentmethod;
	
	@Column(name = "created_at", insertable = false, updatable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")

	private java.sql.Timestamp created_at;

	@Column(name = "Update_at", insertable = false, updatable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")

	private java.sql.Timestamp Update_at;
	
	private String Update_by;

	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Package> packages = new ArrayList<>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getCharge() {
		return charge;
	}

	public void setCharge(Long charge) {
		this.charge = charge;
	}

	public String getInstructions() {
		return Instructions;
	}

	public void setInstructions(String instructions) {
		Instructions = instructions;
	}

	public PaymentMethod getPaymentmethod() {
		return paymentmethod;
	}

	public void setPaymentmethod(PaymentMethod paymentmethod) {
		this.paymentmethod = paymentmethod;
	}

	public List<Package> getPackages() {
		return packages;
	}

	public void setPackages(List<Package> packages) {
		this.packages = packages;
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

	
	
}
