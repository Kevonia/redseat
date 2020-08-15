package com.kovecmedia.redseat.entity;

import java.sql.Date;
import java.sql.Time;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicUpdate;

import com.kovecmedia.redseat.model.JobStatus;

@Entity
@Table(name = "jobhistory")
@DynamicUpdate
public class JobHistory {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private JobStatus status;
	private Date rundate;
	private Time runtime;
	public ScheduledJob getScheduledId() {
		return scheduledId;
	}

	public void setScheduledId(ScheduledJob scheduledId) {
		this.scheduledId = scheduledId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "scheduledId")
	private ScheduledJob scheduledId;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public JobStatus getStatus() {
		return status;
	}

	public void setStatus(JobStatus status) {
		this.status = status;
	}

	public Date getRundate() {
		return rundate;
	}

	public void setRundate(Date rundate) {
		this.rundate = rundate;
	}

	public Time getRuntime() {
		return runtime;
	}

	public void setRuntime(Time runtime) {
		this.runtime = runtime;
	}
	
	

}
