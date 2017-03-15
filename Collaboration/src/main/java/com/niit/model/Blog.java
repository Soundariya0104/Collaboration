package com.niit.model;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.stereotype.Component;

@Entity
@Table(name="BLOG_TABLE")
@Component
public class Blog extends BaseDomain {

	@Id
	private String blogname;
	private String blogdescription;
	private String username;
	
@Transient
	@Column(name="date_time")
	private Date blogdatetime;
	private char blogstatus;
	private String blogreason;
	public String getBlogname() {
		return blogname;
	}
	public void setBlogname(String blogname) {
		this.blogname = blogname;
	}
	public String getUsename() {
		return username;
	}

	
	public String getBlogdescription() {
		return blogdescription;
	}
	public void setBlogdescription(String blogdescription) {
		this.blogdescription = blogdescription;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
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
	public String getBlogreason() {
		return blogreason;
	}
	public void setBlogreason(String blogreason) {
		this.blogreason = blogreason;
	}
	
	
	
	
	
}
