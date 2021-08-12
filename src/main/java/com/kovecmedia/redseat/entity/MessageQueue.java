package com.kovecmedia.redseat.entity;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicUpdate;

import com.kovecmedia.redseat.model.MessageStatus;

@Entity
@Table(name = "messagequeue")
@DynamicUpdate
public class MessageQueue {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String email;
	private String phonenumber;
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "scheduledId")
	private ScheduledJob scheduledId;
	private Date rundate;
	private MessageStatus status;
	
	@Column(name = "package_id", insertable = true, columnDefinition = "BIGINT DEFAULT 0")
	private Long packageID;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhonenumber() {
		return phonenumber;
	}

	public void setPhonenumber(String phonenumber) {
		this.phonenumber = phonenumber;
	}

	public ScheduledJob getScheduledId() {
		return scheduledId;
	}

	public void setScheduledId(ScheduledJob scheduledId) {
		this.scheduledId = scheduledId;
	}

	public Date getRundate() {
		return rundate;
	}

	public void setRundate(Date rundate) {
		this.rundate = rundate;
	}

	public MessageStatus getStatus() {
		return status;
	}

	public void setStatus(MessageStatus status) {
		this.status = status;
	}

	public Long getPackageID() {
		return packageID;
	}

	public void setPackageID(Long packageID) {
		this.packageID = packageID;
	}

}
