package com.niit.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.niit.model.Friend;

@Repository
public interface FriendDAO {
	
	public boolean save(Friend friend);
	public boolean update(Friend friend);
	public void delete(String userid,String friendid);
	public void setOnLine(String userid);
	public void setOffLine(String userid);
	public Friend get(String userid,String friendid);
	public List<Friend> getmyfriends(String userid);
	public List<Friend> getNewFriendrequest(String userid);
	

}