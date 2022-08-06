package com.kovecmedia.redseat.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity(name = "manifests_items")
@Table(name = "manifests_items")
public class ManifestItems {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String houseAWB;
	
	private String shipper;
	
	private String consignee;
	
	private Long pcs;
	
	private String description;
	
	private String wtlbs;
	
	private String wtkg;
	

	@Column(name = "created_at", insertable = false, updatable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")

	private java.sql.Timestamp created_at;

	@Column(name = "updated_at", insertable = false, updatable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")

	private java.sql.Timestamp Update_at;
	
	@ManyToOne(fetch = FetchType.EAGER )
	@JoinColumn(name = "manifestId")
	private Manifest  manifestId;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getHouseAWB() {
		return houseAWB;
	}

	public void setHouseAWB(String houseAWB) {
		this.houseAWB = houseAWB;
	}

	public String getShipper() {
		return shipper;
	}

	public void setShipper(String shipper) {
		this.shipper = shipper;
	}

	public String getConsignee() {
		return consignee;
	}

	public void setConsignee(String consignee) {
		this.consignee = consignee;
	}

	public Long getPcs() {
		return pcs;
	}

	public void setPcs(Long pcs) {
		this.pcs = pcs;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getWtlbs() {
		return wtlbs;
	}

	public void setWtlbs(String wtlbs) {
		this.wtlbs = wtlbs;
	}

	public String getWtkg() {
		return wtkg;
	}

	public void setWtkg(String wtkg) {
		this.wtkg = wtkg;
	}

	public Manifest getManifestId() {
		return manifestId;
	}

	public void setManifestId(Manifest manifestId) {
		this.manifestId = manifestId;
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

	public ManifestItems(Long id, String houseAWB, String shipper, String consignee, Long pcs, String description,
			String wtlbs, String wtkg, Manifest manifestId) {
		super();
		this.id = id;
		this.houseAWB = houseAWB;
		this.shipper = shipper;
		this.consignee = consignee;
		this.pcs = pcs;
		this.description = description;
		this.wtlbs = wtlbs;
		this.wtkg = wtkg;
		this.manifestId = manifestId;
	}

	public ManifestItems() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
