package com.windcloud.controller;

import javax.annotation.security.RolesAllowed;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class WindCloudController {
	
	@GetMapping("/")
	@RolesAllowed("SUPER_ADMIN")
	public String welcome()
	{
		return "WelCome in Lottery Appplication";
	}

}
