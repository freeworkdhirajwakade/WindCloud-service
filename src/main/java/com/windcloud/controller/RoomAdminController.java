package com.windcloud.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.windcloud.dto.DepositAmountDTO;
import com.windcloud.service.TransactionService;

@org.springframework.web.bind.annotation.RestController
@RequestMapping("/roomadmin")
@CrossOrigin
public class RoomAdminController {
	
	
	@Autowired
	private TransactionService transactionService;
	
	@RequestMapping(value = "/hi", method = RequestMethod.GET)	
	public String sayhello()
	{
		return "Welcome Room Administrator";
	}
	
	//@PreAuthorize("hasRole('ROOM_ADMIN')")
	@RequestMapping(value = "/depositAmount", method = RequestMethod.POST)	
	public ResponseEntity<?>deposit(@RequestBody DepositAmountDTO depositAmount)
	{
		return transactionService.depositStorePoints(depositAmount);
	}

}
