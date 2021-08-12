package com.kovecmedia.redseat.entity;

import java.util.Set;

import javax.persistence.Column;
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


	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "package")
	private Package packageId;

	@ManyToMany(fetch = FetchType.EAGER)
	private Set<Fee> Fees;

	
	@Column(name = "created_at", insertable = false, updatable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")

	private java.sql.Timestamp created_at;

	@Column(name = "Updated_at", insertable = false, updatable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")

	private java.sql.Timestamp Update_at;
	

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
