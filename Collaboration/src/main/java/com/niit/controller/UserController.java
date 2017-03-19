package com.niit.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.niit.dao.UserDAO;
import com.niit.model.User;



@RestController
public class UserController {

	
	@Autowired
	UserDAO userDAO;
	
	@Autowired
	User user;
	
	@RequestMapping(value = "/getAllUser", method = RequestMethod.GET)
	public ResponseEntity<List<User>> getAllUser(){  
		List<User> userobjlist= userDAO.list();        
	
		if(userobjlist.isEmpty()){
		user.setErrorCode("100");
		user.setErrorMessage("Not user are available");
		userobjlist.add(user);
		return new ResponseEntity <List<User>>(userobjlist,HttpStatus.OK);
		}
		user.setErrorCode("200");
		user.setErrorMessage("User is available");
			return new ResponseEntity <List<User>>(userobjlist,HttpStatus.OK);
		}
	
	
	
	
	
	@RequestMapping(value = "/validate", method = RequestMethod.POST)
	public ResponseEntity<User> validateCredentials(@RequestBody User user, HttpSession session){
		
		if(userDAO.validate(user.getUsername(), user.getPassword()) == null){
			user=new User();
			user.setErrorCode("404");
			user.setErrorMessage("Invalid Credential...please try again");
			
		}else{
			user.setErrorCode("200");
			user.setErrorMessage("You are successfully logged in ....");
			session.setAttribute("Username", user.getUsername());
			
				}
return new ResponseEntity<User>(user, HttpStatus.OK);
}

	
	@RequestMapping(value = "/register", method = RequestMethod.POST)
		public ResponseEntity<User> Register(@RequestBody User user){
		if(userDAO.get(user.getUsername())==null){
			userDAO.save(user);
			user.setErrorCode("200");
			user.setErrorMessage("Successfully registered");}
		else{user.setErrorCode("400");
		user.setErrorMessage("User Exist with name "+user.getUsername());
		}
			return new ResponseEntity<User>(user,HttpStatus.OK);
		
}
	@GetMapping("/hello")
	public String Hello(){
		userDAO.save(user);
		user.setErrorCode("200");
		user.setErrorMessage("Successfully registered");
	
		return "hello";
	}
	
	@GetMapping("/getUser/{username}")
	public ResponseEntity<User> getUser(@PathVariable("username") String username){
		user=userDAO.get(username);
		if(user==null){
			user= new User();
			user.setErrorCode("404");
			user.setErrorMessage("No user found for "+username);
		}
	return new ResponseEntity<User>(user,HttpStatus.OK);  
			}
	
}














