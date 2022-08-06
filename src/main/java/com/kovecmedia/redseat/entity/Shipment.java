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
import javax.persistence.Table;

@Entity
@Table(name = "shipments")
public class Shipment {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String Receivedby;
	
	private String NumberOfItems;
	
     
	private String Customers;
	
	private java.sql.Timestamp dateReceived;
	
	@Column(name = "created_at", insertable = false, updatable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
	
	private java.sql.Timestamp created_at;
	@Column(name = "updated_at", insertable = false, updatable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
	
	private java.sql.Timestamp Update_at;
	public String getReceivedby() {
		return Receivedby;
	}
	public void setReceivedby(String receivedby) {
		Receivedby = receivedby;
	}
	public String getNumberOfItems() {
		return NumberOfItems;
	}
	public void setNumberOfItems(String numberOfItems) {
		NumberOfItems = numberOfItems;
	}
	
	public java.sql.Timestamp getDateReceived() {
		return dateReceived;
	}
	public void setDateReceived(java.sql.Timestamp dateReceived) {
		this.dateReceived = dateReceived;
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
	public String getCustomers() {
		return Customers;
	}
	public void setCustomers(String customers) {
		Customers = customers;
	}
	


}
