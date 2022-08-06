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
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
@Table(name = "users")
@SequenceGenerator(name="user_id_seq", initialValue=883, allocationSize=1)
public class User {
	@Id
	 @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="user_id_seq")
	private Long id;

	@Column(name = "points", insertable = true, columnDefinition = "BIGINT DEFAULT 0")
	private Long points;

	private String name;
	@JsonIgnore
	private String password;

	@Column(name = "isActive", insertable = false, updatable = true, columnDefinition = "boolean DEFAULT true")
	private Boolean isActive;

	@Column(unique = true)
	private String email;

	@Column(name = "isCustomer")
	private Boolean isCustomer;
    
	@JsonIgnore
	@Transient
	private String passwordConfirm;

	@ManyToMany(fetch = FetchType.EAGER)
	private Set<Role> roles;

	@OneToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "users_phone",joinColumns = { @JoinColumn(name = "user_id") }, inverseJoinColumns = { @JoinColumn(name = "contactnumber_id") })
	private Set<ContactNumber> phone;

	@ManyToMany(fetch = FetchType.LAZY)
	private Set<Address> address;

	@Column(name = "created_at", insertable = false, updatable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")

	private java.sql.Timestamp createdAt;

	@Column(name = "updated_at", insertable = false, updatable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")

	private java.sql.Timestamp updatedAt;

	private String updateBy;
	
	private Long refCode;

	public Long getRefCode() {
		return refCode;
	}

	public void setRefCode(Long refCode) {
		this.refCode = refCode;
	}

	public String getUpdateBy() {
		return updateBy;
	}

	public void setUpdateBy(String updateBy) {
		this.updateBy = updateBy;
	}

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinColumn(name = "Shipments")
	private  Set<Shipment> Shipments;
	
	
	@ManyToOne(fetch = FetchType.EAGER )
	@JoinColumn(name = "groupId")
	private Group groupId;


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

	@JsonIgnore
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = passwordEncoder().encode(password);
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@JsonIgnore
	public String getPasswordConfirm() {
		return passwordConfirm;
	}

	public void setPasswordConfirm(String passwordConfirm) {
		this.passwordConfirm = passwordConfirm;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	public Set<ContactNumber> getPhone() {
		return phone;
	}

	public void setPhone(Set<ContactNumber> phone) {
		this.phone = phone;
	}

	public Set<Address> getAddress() {
		return address;
	}

	public void setAddress(Set<Address> address) {

		this.address = address;
	}



	public Long getPoints() {
		return this.points;
	}

	public void setPoints(Long points) {
		this.points = points;
	}
	
	public void addPoints(Long points) {
		this.points = points + this.points;
	}

	@JsonIgnore
	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}



	public User(Long id, Long points, String name, String password, Boolean isActive, String email, Boolean isCustomer,
			String passwordConfirm, Set<Role> roles, Set<ContactNumber> phone, Set<Address> address,
			Timestamp createdAt, Timestamp updatedAt, String updateBy, long refCode, Set<Shipment> shipments,
			Group groupId) {
		super();
		this.id = id;
		this.points = points;
		this.name = name;
		this.password = password;
		this.isActive = isActive;
		this.email = email;
		this.isCustomer = isCustomer;
		this.passwordConfirm = passwordConfirm;
		this.roles = roles;
		this.phone = phone;
		this.address = address;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
		this.updateBy = updateBy;
		this.refCode = refCode;
		Shipments = shipments;
		this.groupId = groupId;
	}
	
	

	@Override
	public String toString() {
		return "User [id=" + id + ", points=" + points + ", name=" + name + ", password=" + password + ", isActive="
				+ isActive + ", email=" + email + ", isCustomer=" + isCustomer + ", passwordConfirm=" + passwordConfirm
				+ ", roles=" + roles + ", phone=" + phone + ", address=" + address + ", createdAt=" + createdAt
				+ ", updatedAt=" + updatedAt + ", updateBy=" + updateBy + ", refCode=" + refCode + ", Shipments="
				+ Shipments + ", groupId=" + groupId + "]";
	}

	public User() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

}