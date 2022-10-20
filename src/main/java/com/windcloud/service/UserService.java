package com.windcloud.service;

import org.springframework.http.ResponseEntity;

import com.windcloud.config.Response;
import com.windcloud.dto.UserDTO;
import com.windcloud.entity.User;

public interface UserService {
	
	@SuppressWarnings("rawtypes")
	public ResponseEntity<Response>signupUser(UserDTO userDto);
	@SuppressWarnings("rawtypes")
	public ResponseEntity<Response>signInUser(UserDTO userDto);
	public User findbyEmailId(String username);

}
