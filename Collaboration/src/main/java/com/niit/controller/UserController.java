	package com.niit.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.niit.dao.UserDAO;
import com.niit.model.User;

@RestController
	public class UserController {
		
		@Autowired
		UserDAO userDAO;
  
		@Autowired
		User user;
		@GetMapping("/getAllUsers")
		public ResponseEntity<List<User>> getAllUsers()
		{
			List users=userDAO.list();
			if(users.isEmpty()){
				user.setError("100");
				user.setErrorMessage("No users are available");
			    users.add(user);
			    return new ResponseEntity<List<User>>(users.HttpStatus.OK);
			}
			user.setErrorCode("200");
			user.setErrorMessage("Successfully fetched the user");
			return new ResponseEntity<List<User>>(users,HttpStatus.OK);
		}
	}

