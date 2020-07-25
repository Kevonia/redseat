package com.kovecmedia.redseat.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "authorizedpersons")
public class AuthorizedPickup {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	private String idnumber;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_type")
	private IDTYPE idtype;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	private User user;

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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIdnumber() {
		return idnumber;
	}

	public void setIdnumber(String idnumber) {
		this.idnumber = idnumber;
	}

	public IDTYPE getIdtype() {
		return idtype;
	}

	public void setIdtype(IDTYPE idtype) {
		this.idtype = idtype;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
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

	@Column(columnDefinition = "datetime default 'John Snow'")
	public String getUpdate_by() {
		return Update_by;
	}

	public void setUpdate_by(String update_by) {
		Update_by = update_by;
	}

}