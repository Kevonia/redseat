package com.kovecmedia.redseat.entity;

import java.sql.Date;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import com.kovecmedia.redseat.model.BillingStatus;

@Entity(name = "billing")
public class Billing {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String description;
	private double value;
	private BillingStatus status;
	private Date updated;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "package")
	private Package packageId;

	@ManyToMany(fetch = FetchType.EAGER)
	private Set<Fee> Fees;


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public double getValue() {
		return value;
	}

	public void setValue(double value) {
		this.value = value;
	}

	public BillingStatus getStatus() {
		return status;
	}

	public void setStatus(BillingStatus status) {
		this.status = status;
	}

	public Date getUpdated() {
		return updated;
	}

	public void setUpdated(Date updated) {
		this.updated = updated;
	}

	public Package getPackageId() {
		return packageId;
	}

	public void setPackageId(Package packageId) {
		this.packageId = packageId;
	}

	public Set<Fee> getFee() {
		return Fees;
	}

	public void setFee(Set<Fee> fee) {
		Fees = fee;
	}







}
