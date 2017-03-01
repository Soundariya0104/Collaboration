package com.niit.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

@Entity
@Table(name="User")
@Component
public class User extends BaseDomain implements Serializable{
	
	@Id
	private String username;
	@Column
	private String firstname;
	@Column
	private String secondname;
	@Column
	private String password;
	@Column
	private String email;
	@Column
	private String mobile;
	@Column
	private char status;
	@Column
	private char is_online;
	
	
	
	
	public String getPassword() {
		return password;
	}
	public void setPasssword(String passsword) {
		this.password = passsword;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public char getStatus() {
		return status;
	}
	public void setStatus(char status) {
		this.status = status;
	}
	public char getIs_online() {
		return is_online;
	}
	public void setIs_online(char is_online) {
		this.is_online = is_online;
	}
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getSecondname() {
		return secondname;
	}
	public void setSecondname(String secondname) {
		this.secondname = secondname;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
}
