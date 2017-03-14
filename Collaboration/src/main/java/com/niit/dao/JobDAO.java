package com.niit.dao;

import java.util.List;

import com.niit.model.Job;

public interface JobDAO {
	
	public Job getJob(String Jobname);
	
	public List<Job> getAllJob();
	
	public List<Job> getAllJobs(String username);
	
	public boolean saveJob(Job Job);
	
	public boolean update(Job Job);

}