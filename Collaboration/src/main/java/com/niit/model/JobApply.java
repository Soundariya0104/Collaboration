package com.niit.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

@Entity
@Table(name="JOBAPPLY_TABLE")
@Component
public class JobApply {

	@Id
	@GeneratedValue
	int jobapplyid;
	 String username;
	 String jobname;
	public int getJobapplyid() {
		return jobapplyid;
	}
	public void setJobapplyid(int jobapplyid) {
		this.jobapplyid = jobapplyid;
	}
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
	 
	 
	 
	
}