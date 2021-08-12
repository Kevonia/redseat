package com.kovecmedia.redseat.entity;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.kovecmedia.redseat.model.FeeType;


@Entity(name = "fees")
@Table(name = "fees")
public class Fee {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String name;

	private double valueUsd;

	private double valueJmd;

	private double lowerLimit;

	private double upperLimit;
	
	@Column(name = "created_at", insertable = false, updatable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")

	private java.sql.Timestamp created_at;

	@Column(name = "updated_at", insertable = false, updatable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")

	private java.sql.Timestamp updated_at;

	private FeeType type;

	@ManyToMany(fetch = FetchType.LAZY)
	private Set<Billing> billing;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<Billing> getBilling() {
		return billing;
	}

	public void setBilling(Set<Billing> billing) {
		this.billing = billing;
	}

	public double getLowerLimit() {
		return lowerLimit;
	}

	public void setLowerLimit(double lowerLimit) {
		this.lowerLimit = lowerLimit;
	}

	public double getUpperLimit() {
		return upperLimit;
	}

	public void setUpperLimit(double upperLimit) {
		this.upperLimit = upperLimit;
	}

	public FeeType getType() {
		return type;
	}

	public void setType(FeeType type) {
		this.type = type;
	}

	public double getValueUsd() {
		return valueUsd;
	}

	public void setValueUsd(double valueUsd) {
		this.valueUsd = valueUsd;
	}

	public double getValueJmd() {
		return valueJmd;
	}

	public void setValueJmd(double valueJmd) {
		this.valueJmd = valueJmd;
	}

}
