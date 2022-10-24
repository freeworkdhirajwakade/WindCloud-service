package com.windcloud.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.windcloud.dto.DepositAmountDTO;

@org.springframework.web.bind.annotation.RestController
@RequestMapping("/roomadmin")
@CrossOrigin
@PreAuthorize("hasRole('ROOM_ADMIN')")
public class RoomAdminController {
	
	@RequestMapping(value = "/hi", method = RequestMethod.GET)	
	public String sayhello()
	{
		return "Welcome Room Administrator";
	}
	
	@RequestMapping(value = "/depositAmount", method = RequestMethod.POST)	
	public ResponseEntity<?>deposit(@RequestBody DepositAmountDTO depositAmount)
	{
		return null;
	}

}
