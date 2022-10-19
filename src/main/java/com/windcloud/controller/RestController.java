package com.windcloud.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.windcloud.dto.UserDTO;
import com.windcloud.service.UserService;

@org.springframework.web.bind.annotation.RestController
@RequestMapping("/api")
public class RestController {

	
	@Autowired
	private UserService userService;
	
	@GetMapping("/")
	public String welcome()
	{
		return "WelCome in Lottery Appplication";
	}
	
	@PostMapping(value = "/signup")
	public ResponseEntity<?>signUpUser(@RequestBody UserDTO userDto)
	{
		return userService.signupUser(userDto);
	}
}
