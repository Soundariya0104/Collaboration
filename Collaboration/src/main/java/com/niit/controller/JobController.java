package com.niit.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.niit.dao.JobDAO;
import com.niit.model.Job;


@RestController
public class JobController {

	private static final Logger logger = 
			LoggerFactory.getLogger(JobController.class);
	

	@Autowired
	private Job job;
	@Autowired
	private JobDAO jobDAO;
	
	@GetMapping("/fetchallJobs")
	public List<Job> getallJob(){
		return jobDAO.getAllJob();
	}
	
	@PostMapping(value = "/createJob")
	public ResponseEntity<Job> createJob(@RequestBody Job Job, HttpSession session) {
		System.out.println("hii");
		
		
		if(jobDAO.saveJob(Job)){
			Job=new Job();
			Job.setErrorCode("200");
			System.out.println("hii1");
			Job.setErrorMessage("Job created");
			
		}else{
			Job.setErrorCode("400");
			Job.setErrorMessage("Job not created , try again....");

				}

		return new ResponseEntity<Job>(Job, HttpStatus.OK);
	}

	
	@GetMapping("/getJobbyname/{Jobname}")
	public Job getJobbyname(@PathVariable("Jobname") String Jobname) {
		logger.debug("inside getJobbyname JobController ");
		Job Job = jobDAO.getJob(Jobname);
		
		if(Job==null)
		{
			Job = new Job();
			Job.setErrorCode("404");
			Job.setErrorMessage("Job not found with the id:" + Jobname);
		}
		
		return Job;
			}
}