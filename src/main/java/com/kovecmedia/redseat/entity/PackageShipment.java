package com.kovecmedia.redseat.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;

@Entity(name="package_shipments")
public class PackageShipment {


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String name;
	
	@OneToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "package_shipment_packags",joinColumns = { @JoinColumn(name = "package_shipment_id") }, inverseJoinColumns = { @JoinColumn(name = "packags_id") })
	private List<Package> packags;
	
	@Column(name = "created_at", insertable = false, updatable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")

	private java.sql.Timestamp created_at;

	@Column(name = "updated_at", insertable = false, updatable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")

	private java.sql.Timestamp updated_at;

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

	public List<Package> getPackags() {
		return packags;
	}

	public void setPackags(List<Package> packags) {
		this.packags = packags;
	}

	public java.sql.Timestamp getCreated_at() {
		return created_at;
	}

	public void setCreated_at(java.sql.Timestamp created_at) {
		this.created_at = created_at;
	}

	public java.sql.Timestamp getUpdated_at() {
		return updated_at;
	}

	public void setUpdated_at(java.sql.Timestamp updated_at) {
		this.updated_at = updated_at;
	}
	
	
}
