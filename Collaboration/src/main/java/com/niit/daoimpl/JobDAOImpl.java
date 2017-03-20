package com.niit.daoimpl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import com.niit.dao.JobDAO;
import com.niit.model.Job;
import com.niit.model.JobApply;


@EnableTransactionManagement
@Repository("JobDAO")
public class JobDAOImpl implements JobDAO{
	
	@Autowired
	private SessionFactory sessionFactory;
	
	public JobDAOImpl(SessionFactory sessionFactory){
		this.sessionFactory=sessionFactory;
	}

	
	//------------------------------------------------------GET Job---------------------------------------------------------------
	@Transactional
	public Job getJob(String Jobname){
		return (Job) sessionFactory.getCurrentSession().get(Job.class, Jobname);
	
	}
	
	
	//------------------------------------------------------GET ALL Job---------------------------------------------------------------
	@SuppressWarnings({ "deprecation", "unchecked" })
	@Transactional
	public List<Job> getAllJob(){
		Query<Job> query = sessionFactory.getCurrentSession().createQuery("from Job");
	return query.list();
	}
	
	//------------------------------------------------------GET all Job of by user---------------------------------------------------------------
	@Transactional
	public List<Job> getAllJobs(String username){
		String hql="from Job where username='"+username+"'";
	Query<Job> query = sessionFactory.getCurrentSession().createQuery(hql);
return query.list();
	}
	
	//------------------------------------------------------SAVE Job---------------------------------------------------------------
	@Transactional
	public boolean saveJob(Job Job){
		try{
			sessionFactory.getCurrentSession().saveOrUpdate(Job);
			return true;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}
	
	//------------------------------------------------------update Job---------------------------------------------------------------
	@Transactional
	public boolean update(Job Job){
		return false;
	}
	@Transactional
	public void deletejob(String jobname){
		Session session=sessionFactory.openSession();
	Job job= new Job();
	
		String hql="from Job where jobname='"+jobname+"'";
			job=(Job) session.createQuery(hql).uniqueResult();
			sessionFactory.getCurrentSession().delete(job);
				}
	@Transactional
	public void applyjob(JobApply jobApply){
		sessionFactory.getCurrentSession().save(jobApply);	

	}
	
}
