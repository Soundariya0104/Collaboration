package com.niit.controller;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import com.niit.model.Message;
import com.niit.model.OutputMessage;





@Controller
public class ChatController {
	private static final Logger logger = 
			LoggerFactory.getLogger(ChatController.class);
	  
	

  @MessageMapping("/chat")   
	  @SendTo("/topic/message")       
	  public OutputMessage sendMessage(Message message) {
	  System.out.println("hii am inside chatcontroller backend");
	    return new OutputMessage(message, new Date()); 
	  }
	  
	

}






