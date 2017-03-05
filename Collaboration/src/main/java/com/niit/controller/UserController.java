package com.niit.controller;

import java.util.List;

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
	
	public ResponseEntity<List<User>> getAllUser(){  //ResponseEntity constructor, if we pass java object, it returns json object
		List<User> userobjlist= userDAO.list();        //need to convert into json objects
	
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
	
	
	
	
	
	
//	
//	@GetMapping("/validate/{username}/{password}")
//	public ResponseEntity<UserModel> validateCredentials(@PathVariable("username")String username, @PathVariable("password") String password){
//		
//		if(userDAO.validate(username, password) == null){
//			userModel=new UserModel();
//			userModel.setErrorCode("404");
//			userModel.setErrorMessage("Invalid Credential..password..plese try again");
//		
//		}else{
//			userModel.setErrorCode("200");
//			userModel.setErrorMessage("You aer succesfully logged in ....");
//		}
//return new ResponseEntity<UserModel>(userModel, HttpStatus.OK);
//}
//	
	
	@RequestMapping(value = "/validate", method = RequestMethod.POST)
	public ResponseEntity<User> validateCredentials(@RequestBody User user){
		
		if(userDAO.validate(user.getUsername(), user.getPassword()) == null){
			user=new User();
			user.setErrorCode("404");
			user.setErrorMessage("Invalid Credential..password..plese try again");
			
		}else{
			user.setErrorCode("200");
			user.setErrorMessage("You aer succesfully logged in ....");

				}
return new ResponseEntity<User>(user, HttpStatus.OK);
}

	
	@PostMapping(value="/register")
	public ResponseEntity<User> Register(@RequestBody User user){
		if(userDAO.get(user.getUsername())==null){
			userDAO.save(user);
			user.setErrorCode("200");
			user.setErrorMessage("Successfully registered");}
		else{
			user.setErrorCode("400");
		user.setErrorMessage("User Exist with name "+user.getUsername());
		}
			return new ResponseEntity<User>(user,HttpStatus.OK);
		
}
	@GetMapping("/hello")
	public String rajesh(){
		userDAO.save(user);
		user.setErrorCode("200");
		user.setErrorMessage("Successfully registered");
	
		return "hwllo rajesh, how u doing";
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