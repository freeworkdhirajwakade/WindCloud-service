package com.windcloud.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.windcloud.dto.DepositAmountDTO;
import com.windcloud.dto.RoomDTO;
import com.windcloud.service.RoomService;
import com.windcloud.service.TransactionService;
import com.windcloud.service.UserService;

@org.springframework.web.bind.annotation.RestController
@RequestMapping("/room_admin")
@CrossOrigin
public class RoomAdminController {
	
	
	@Autowired
	private TransactionService transactionService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private RoomService roomService;
	
	@RequestMapping(value = "/hi", method = RequestMethod.GET)	
	public String sayhello()
	{
		return "Welcome Room Administrator";
	}
	
	@RequestMapping(value = {"/approve_user/{emailId}"}, method = RequestMethod.GET)
	public ResponseEntity<?> approve_user(@PathVariable String emailId) throws Exception 
	{
		return userService.approve_user(emailId);
	}


}
