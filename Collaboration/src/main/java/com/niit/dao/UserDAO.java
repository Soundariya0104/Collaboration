package com.niit.dao;

import java.util.List;

import com.niit.model.User;



public interface UserDAO{

	public User get(String userid);
	public User  validate(String userid, String password);
	public boolean save(User user);
	public List<User> list();

}
