package com.niit.dao;

import java.util.List;

import com.niit.model.Friend;

public interface FriendDAO {
	public void addfriend(Friend friend);
	public List<Friend> notifications(String username);
	public void acceptfriend(Friend friend);
	public List<Friend> friendslist(String username);
	public void unfriend(String username1, String username2);

}