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
import com.niit.model.JobApply;


@RestController
public class JobController {

	private static final Logger logger = 
			LoggerFactory.getLogger(JobController.class);
	

	@Autowired
	private Job job;
	@Autowired
	private JobDAO jobDAO;
	@Autowired
	private JobApply jobApply;
	
	@GetMapping("/fetchallJobs")
	public List<Job> getallJob(){
		return jobDAO.getAllJob();
	}
	
	@PostMapping(value = "/createJob")
	public ResponseEntity<Job> createJob(@RequestBody Job Job, HttpSession session) {
		System.out.println("username:" + job);
//		String loggedInUserID = (String) session.getAttribute("loggedInUserID");
//		Jobmodel.setUserid(loggedInUserID);
//		Jobmodel.setJobstatus('N');// A->Accepted,  R->Rejected
		
		
		if(jobDAO.saveJob(Job)){
			job=new Job();
			job.setErrorCode("200");
			job.setErrorMessage("Job created");
			
		}else{
			job.setErrorCode("400");
			job.setErrorMessage("Job not created ok, try again....");

				}

		return new ResponseEntity<Job>(Job, HttpStatus.OK);
	}

	
	@GetMapping("/getJobbyname/{Jobname}")
	public Job getJobbyname(@PathVariable("Jobname") String Jobname) {
		logger.debug("inside getJobbyname JobController ");
		Job job = jobDAO.getJob(Jobname);
		
		if(job==null)
		{
			job = new Job();
			job.setErrorCode("404");
			job.setErrorMessage("Job not found with the id:" + Jobname);
		}
		
		return job;
			}

@GetMapping(value = "/deletejob/{jobname}")
	public void deleteBlog(@PathVariable("jobname")String jobname, HttpSession session) {
		
	System.out.println("hii am job"+jobname);
	jobDAO.deletejob(jobname);
}

@GetMapping(value = "/applyjob/{jobname}")
public void apply(@PathVariable("jobname")String jobname, HttpSession session) {
String username = (String) session.getAttribute("Username");
jobApply.setUsername(username);
jobApply.setJobname(jobname);
jobDAO.applyjob(jobApply);

}
}
