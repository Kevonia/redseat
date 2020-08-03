package com.kovecmedia.redseat.entity;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.kovecmedia.redseat.model.JobType;

@Entity
@Table(name = "scheduledjobs")
public class ScheduledJob {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private JobType type;
	private String templateName;
	private Date rundate;
	private String status;

	public ScheduledJob() {
		super();

	}

	public ScheduledJob(Long id, JobType type, String templateName, Date rundate, String status) {
		super();
		this.id = id;
		this.type = type;
		this.templateName = templateName;
		this.rundate = rundate;
		this.status = status;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public JobType getType() {
		return type;
	}

	public void setType(JobType type) {
		this.type = type;
	}

	public String getTemplateName() {
		return templateName;
	}

	public void setTemplateName(String templateName) {
		this.templateName = templateName;
	}

	public Date getRundate() {
		return rundate;
	}

	public void setRundate(Date rundate) {
		this.rundate = rundate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
