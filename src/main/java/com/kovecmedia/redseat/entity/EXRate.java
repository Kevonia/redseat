package com.kovecmedia.redseat.entity;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity(name = "ExRateHistory")
@Table(name = "ExRateHistory")
public class EXRate {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private float exrate;

	private Date exratedate;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public float getExrate() {
		return exrate;
	}

	public void setExrate(float exrate) {
		this.exrate = exrate;
	}

	public Date getExratedate() {
		return exratedate;
	}

	public void setExratedate(Date exratedate) {
		this.exratedate = exratedate;
	}
	
	@Column(name = "created_at", insertable = false, updatable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")

	private java.sql.Timestamp created_at;

	@Column(name = "Updated_at", insertable = false, updatable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")

	private java.sql.Timestamp Update_at;
	
}
