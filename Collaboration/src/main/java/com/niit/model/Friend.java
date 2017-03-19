package com.niit.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

@Entity
@Table(name="FRIEND_TABLE")
@Component
public class Friend extends BaseDomain {
	@Id
	@GeneratedValue
	int friendid;
	
	String username1;
	String username2;
	char friendstatus;

	
	
	public String getUsername1() {
		return username1;
	}
	public int getFriendid() {
		return friendid;
	}
	public void setFriendid(int friendid) {
		this.friendid = friendid;
	}
	public void setUsername1(String username1) {
		this.username1 = username1;
	}
	public String getUsername2() {
		return username2;
	}
	public void setUsername2(String username2) {
		this.username2 = username2;
	}
	public char getFriendstatus() {
		return friendstatus;
	}
	public void setFriendstatus(char friendstatus) {
		this.friendstatus = friendstatus;
	}
	
	

}
