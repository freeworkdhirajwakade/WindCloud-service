package com.windcloud.service;

import org.springframework.http.ResponseEntity;

import com.windcloud.config.Response;
import com.windcloud.dto.UserDTO;

public interface UserService {
	
	public ResponseEntity<Response>signupUser(UserDTO userDto);

}
