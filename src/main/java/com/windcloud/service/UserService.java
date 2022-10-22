package com.windcloud.service;

import org.springframework.http.ResponseEntity;

import com.windcloud.config.Response;
import com.windcloud.dto.UserDTO;
import com.windcloud.entity.User;

public interface UserService {
	
	public ResponseEntity<Response>signupUser(UserDTO userDto);
	public User findbyEmailId(String username);
	public ResponseEntity<Response> signin(UserDTO authenticationRequest) throws Exception;
	public ResponseEntity<Response> forgotPassword(String emailId ) throws Exception;
	ResponseEntity<?> updatePassword(String email, String confirmPassword, String token);

}
