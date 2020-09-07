package com.kovecmedia.redseat.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.kovecmedia.redseat.model.PackageLocation;
import com.kovecmedia.redseat.model.PackagesStatus;

@Entity(name="packages")
//@Table(name = "packages")
public class Package {
	
	

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String trackingNumber;

	private String description;

	private String seller;

	private long weight;

	private double value;

	private PackagesStatus Status;

	private PackageLocation location;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "userId")
	private User userId;

	@Column(name = "created_at", insertable = false, updatable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")

	private java.sql.Timestamp created_at;

	@Column(name = "Update_at", insertable = false, updatable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")

	private java.sql.Timestamp Update_at;

	private String Update_by;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTrackingNumber() {
		return trackingNumber;
	}

	public void setTrackingNumber(String trackingNumber) {
		this.trackingNumber = trackingNumber;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getSeller() {
		return seller;
	}

	public void setSeller(String seller) {
		this.seller = seller;
	}

	public long getWeight() {
		return weight;
	}

	public void setWeight(long weight) {
		this.weight = weight;
	}

	public double getValue() {
		return value;
	}

	public void setValue(double value) {
		this.value = value;
	}

	public PackagesStatus getStatus() {
		return Status;
	}

	public void setStatus(PackagesStatus status) {
		Status = status;
	}

	public PackageLocation getLocation() {
		return location;
	}

	public void setLocation(PackageLocation location) {
		this.location = location;
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
	@JsonIgnore
	public User getUserid() {
		return userId;
	}

	public void setUserId(User userId) {
		this.userId = userId;
	}

	public Package(Long id, String trackingNumber, String description, String seller, long weight, double value,
			PackagesStatus status, PackageLocation location, User user_id, Timestamp created_at, Timestamp update_at,
			String update_by) {
		super();
		this.id = id;
		this.trackingNumber = trackingNumber;
		this.description = description;
		this.seller = seller;
		this.weight = weight;
		this.value = value;
		Status = status;
		this.location = location;
		this.userId = user_id;
		this.created_at = created_at;
		Update_at = update_at;
		Update_by = update_by;
	}

	public Package() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	
}
