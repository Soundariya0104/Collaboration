package com.niit.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

@Entity
@Table(name="JOB_TABLE")
@Component
public class Job extends BaseDomain {

	@Id
	private String jobname;
	
	@Column
	private String jobdescription;
	
	@Column
	private String jobapplications;
	
	@Column
	private String username;
	
	

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getJobname() {
		return jobname;
	}

	public void setJobname(String jobname) {
		this.jobname = jobname;
	}

	public String getJobdescription() {
		return jobdescription;
	}

	public void setJobdescription(String jobdescription) {
		this.jobdescription = jobdescription;
	}

	public String getJobapplications() {
		return jobapplications;
	}

	public void setJobapplications(String jobapplications) {
		this.jobapplications = jobapplications;
	}
	
	
	
	
}