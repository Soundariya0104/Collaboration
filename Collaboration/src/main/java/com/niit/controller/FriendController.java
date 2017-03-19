package com.niit.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.niit.dao.FriendDAO;
import com.niit.model.Friend;


@RestController
public class FriendController {
	
	@Autowired
	Friend friend;
	@Autowired
	FriendDAO friendDAO;
	
@GetMapping("/addfriend/{username}")
public ResponseEntity<Friend> friendrequest(@PathVariable String username, HttpSession session) {
	
	String username1 = (String) session.getAttribute("Username");
	friend.setUsername1(username1);
	friend.setUsername2(username);
	friend.setFriendstatus('w');
	friendDAO.addfriend(friend);
	return new ResponseEntity<Friend>(friend, HttpStatus.OK);
}
	
@GetMapping("/notifications")
public List<Friend> notifications(HttpSession session){
	System.out.println("am inside notification backend controller");
	String username1 = (String) session.getAttribute("Username");
	return friendDAO.notifications(username1);
}

@GetMapping("/acceptfriend/{username}")
public void acceptfriend(@PathVariable String username,HttpSession session){
	System.out.println("am inside acceptfriend controller");
	System.out.println("hiii my boy"+username);
	String username1 = (String) session.getAttribute("Username");
	friend.setUsername1(username);
	friend.setUsername2(username1);
	friendDAO.acceptfriend(friend);
}

@GetMapping("/friendslist")
public List<Friend> friendslist(HttpSession session){
	String username = (String) session.getAttribute("Username");
	return friendDAO.friendslist(username);
	
}
@GetMapping("/unfriend/{username}")
public void unfriend(@PathVariable String username,HttpSession session){
	String username1 = (String) session.getAttribute("Username");
	System.out.println("hii  am inside unfriend controller java"); 
friendDAO.unfriend(username1, username);
	
}

}



