package com.kovecmedia.redseat.entity;

import java.sql.Timestamp;
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
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.kovecmedia.redseat.model.PackageLocation;
import com.kovecmedia.redseat.model.PackagesStatus;

@Entity(name="packages")
//@Table(name = "packages")
public class Package {
	
	

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
    
	@Column(unique=true)
	private String trackingNumber;

	private String description;

	private String reason;
	
	private String seller;

	private double weight;

	private double value;

	private PackagesStatus status;

	private PackageLocation location;
	
	private String invoceUrl;
	
	private boolean preAlert;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "userId")
	private User userId;

	@Column(name = "created_at", insertable = false, updatable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")

	private java.sql.Timestamp created_at;

	@Column(name = "updated_at", insertable = false, updatable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")

	private java.sql.Timestamp updated_at;

	private String updateBy;
	
	
	@Column(name = "email_sent", insertable = true, updatable = true, nullable = true,columnDefinition = "boolean DEFAULT false")
	private boolean emailSent;
	
	@Column(insertable = true, updatable = true, columnDefinition = "string DEFAULT ''")
	private String packageImg;
	
	@Column(name = "type", insertable = true, updatable = true, columnDefinition = "TINYINT DEFAULT 1")
	
	private Shppingtype type;


	
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

	public double getWeight() {
		return weight;
	}

	public void setWeight(double weight) {
		this.weight = weight;
	}

	public double getValue() {
		return value;
	}

	public void setValue(double value) {
		this.value = value;
	}

	public PackagesStatus getStatus() {
		return status;
	}

	public void setStatus(PackagesStatus status) {
		this.status = status;
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
		return updated_at;
	}

	public void setUpdate_at(java.sql.Timestamp update_at) {
		this.updated_at = update_at;
	}

	public String getUpdate_by() {
		return this.updateBy;
	}

	public void setUpdate_by(String update_by) {
		this.updateBy = update_by;
	}
	@JsonIgnore
	public User getUserid() {
		return userId;
	}

	

	public boolean isPreAlert() {
		return preAlert;
	}

	public void setPreAlert(boolean preAlert) {
		this.preAlert = preAlert;
	}

	public User getUserId() {
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
		this.status = status;
		this.location = location;
		this.userId = user_id;
		this.created_at = created_at;
		this.updated_at = update_at;
		this.updateBy = update_by;
	}

	public Package() {
		super();
		// TODO Auto-generated constructor stub
	}

	public boolean isEmailSent() {
		return emailSent;
	}

	public void setEmailSent(boolean emailSent) {
		this.emailSent = emailSent;
	}

	public String getPackageImg() {
		return packageImg;
	}

	public void setPackageImg(String packageImg) {
		this.packageImg = packageImg;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}



	public String getInvoceUrl() {
		return invoceUrl;
	}

	public void setInvoceUrl(String invoceUrl) {
		this.invoceUrl = invoceUrl;
	}

	public java.sql.Timestamp getUpdated_at() {
		return updated_at;
	}

	public void setUpdated_at(java.sql.Timestamp updated_at) {
		this.updated_at = updated_at;
	}

	public String getUpdateBy() {
		return updateBy;
	}

	public void setUpdateBy(String updateBy) {
		this.updateBy = updateBy;
	}

	public Shppingtype getType() {
		return type;
	}

	public void setType(Shppingtype type) {
		this.type = type;
	}

	
	
	
}
