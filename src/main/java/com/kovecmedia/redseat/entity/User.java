package com.kovecmedia.redseat.entity;

import javax.persistence.*;

import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Set;

@Entity
@Table(name = "users")
@SequenceGenerator(name="user_id_seq", initialValue=883, allocationSize=1)
public class User {
	@Id
	 @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="user_id_seq")
	private Long id;

	@Column(name = "points", insertable = false, columnDefinition = "BIGINT DEFAULT 0")
	private Long points;

	private String name;

	private String password;

	@Column(name = "isActive", insertable = false, updatable = true, columnDefinition = "boolean DEFAULT true")
	private Boolean isActive;

	@Column(unique = true)
	private String email;

	@Transient
	private String passwordConfirm;

	@ManyToMany(fetch = FetchType.LAZY)
	private Set<Role> roles;

	@OneToMany(fetch = FetchType.LAZY)
	private Set<ContactNumber> phone;

	@ManyToMany(fetch = FetchType.LAZY)
	private Set<Address> address;

	@Column(name = "created_at", insertable = false, updatable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")

	private java.sql.Timestamp created_at;

	@Column(name = "updated_at", insertable = false, updatable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")

	private java.sql.Timestamp updated_at;

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
		updated_at = update_at;
	}

	public String getUpdate_by() {
		return Update_by;
	}

	public void setUpdate_by(String update_by) {
		Update_by = update_by;
	}

	public Long getPoints() {
		return this.points;
	}

	public void setPoints(Long points) {
		this.points = points;
	}

	@JsonIgnore
	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

	public User(String name, String password, String email, Set<Role> roles, Set<ContactNumber> phone,
			Set<Address> address) {
		super();
		this.name = name;
		this.password = password;
		this.email = email;
		this.roles = roles;
		this.phone = phone;
		this.address = address;

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