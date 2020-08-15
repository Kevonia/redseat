package com.kovecmedia.redseat.entity;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "fees")
public class Fee {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String name;
	
	private double value;
	
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



	public double getValue() {
		return value;
	}

	public void setValue(double value) {
		this.value = value;
	}

	public Set<Billing> getBilling() {
		return billing;
	}

	public void setBilling(Set<Billing> billing) {
		this.billing = billing;
	}
	
	
	
}
