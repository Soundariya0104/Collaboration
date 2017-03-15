package com.niit.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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
public ResponseEntity<Friend> addfriend(@PathVariable String username, HttpSession session) {
	
	String username1 = (String) session.getAttribute("Username");
	friend.setUsername1(username1);
	friend.setUsername2(username);
	friend.setFriendstatus('w');
	friendDAO.addfriend(friend);
	return new ResponseEntity<Friend>(friend, HttpStatus.OK);


}}