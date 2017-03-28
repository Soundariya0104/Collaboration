package com.niit.controller;

import java.util.List;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.niit.config.MailConfig;
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
	
	
	
	
	
	//@GetMapping(value="/validate")
	@RequestMapping(value = "/validate", method = RequestMethod.POST)
	public User validateCredentials(@RequestBody User user, HttpSession session){
User userModel2= new User();
userModel2=userDAO.validate(user.getUsername(), user.getPassword());
		if(userModel2 == null){
			user=new User();
			user.setErrorCode("404");
System.out.println("Invalid Credential..password..please try again");
			user.setErrorMessage("Invalid Credential..password..please try again");
			
		}else{
			
			userDAO.saveonline(userModel2);
			user.setErrorCode("200");
			System.out.println("sucess log in ");
			user.setErrorMessage("You are succesfully logged in ....");
			session.setAttribute("Username", user.getUsername());
			
				}
return userModel2;
}

	
	@SuppressWarnings("resource")
	@RequestMapping(value = "/register", method = RequestMethod.POST)
		public ResponseEntity<User> Register(@RequestBody User user) throws MessagingException {
		if(userDAO.get(user.getUsername())==null){
			userDAO.save(user);
		
			AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
			   ctx.register(MailConfig.class);
			   ctx.refresh();
			  
			   JavaMailSenderImpl mailSender = ctx.getBean(JavaMailSenderImpl.class);
			   MimeMessage mimeMessage = mailSender.createMimeMessage();
		      	   MimeMessageHelper mailMsg = new MimeMessageHelper(mimeMessage);
		      	   mailMsg.setFrom("soundariya0104@gmail.com");
		      	   mailMsg.setTo(user.getEmail());
		      	   mailMsg.setSubject("Activated");
		      	   mailMsg.setText("You are successfull registered");
			   mailSender.send(mimeMessage);
		
			
			
			user.setErrorCode("200");
			user.setErrorMessage("Successfully registered");
		    System.out.println("successfully registered");	
		
		
		}
		else{user.setErrorCode("404");
		System.out.println("not registered");	
		
		user.setErrorMessage("User Exist with name "+user.getUsername());
		}
			return new ResponseEntity<User>(user,HttpStatus.OK);
		
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
	@GetMapping("/logout")
	public void logout(HttpSession session){
		String username = (String) session.getAttribute("Username");
		userDAO.offline(username);
		session.invalidate();

	
	}
	
}
