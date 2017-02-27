package com.niit.model;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.stereotype.Component;

@Entity
@Table(name="BLOG")
@Component
public class Blog extends BaseDomain {

	@Id
	private int blogid;
	private String blogtitle;
	private String blogdescription;
	@Column(name="username")
	private String userid;
	
	@Transient
	@Column(name="date_time")
	private Date blogdatetime;
	private char blogstatus;
	private String blogreaason;
	public int getBlogid() {
		return blogid;
	}
	public void setBlogid(int blogid) {
		this.blogid = blogid;
	}
	public String getBlogtitle() {
		return blogtitle;
	}
	public void setBlogtitle(String blogtitle) {
		this.blogtitle = blogtitle;
	}
	public String getBlogdescription() {
		return blogdescription;
	}
	public void setBlogdescription(String blogdescription) {
		this.blogdescription = blogdescription;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public Date getBlogdatetime() {
		return blogdatetime;
	}
	public void setBlogdatetime(Date blogdatetime) {
		this.blogdatetime = blogdatetime;
	}
	public char getBlogstatus() {
		return blogstatus;
	}
	public void setBlogstatus(char blogstatus) {
		this.blogstatus = blogstatus;
	}
	public String getBlogreaason() {
		return blogreaason;
	}
	public void setBlogreaason(String blogreaason) {
		this.blogreaason = blogreaason;
	}
	
	
	
	
	
}
