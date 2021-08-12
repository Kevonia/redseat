package com.kovecmedia.redseat.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "addresses")
public class Company {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String name;
	
	private String primaryColor;
	
	private String logoURL;
	
	private String tagLine;
	
	@Column(name = "created_at", insertable = false, updatable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")

	private java.sql.Timestamp created_at;

	@Column(name = "Updated_at", insertable = false, updatable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")

	private java.sql.Timestamp Update_at;

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getPrimaryColor() {
		return primaryColor;
	}

	public String getLogoURL() {
		return logoURL;
	}

	public String getTagLine() {
		return tagLine;
	}

	public java.sql.Timestamp getCreated_at() {
		return created_at;
	}

	public java.sql.Timestamp getUpdate_at() {
		return Update_at;
	}
	
	
}
