package com.windcloud.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.windcloud.config.Response;
import com.windcloud.constants.CommanConstants;
import com.windcloud.dto.UserDTO;
import com.windcloud.entity.User;
import com.windcloud.jwt.JwtTokenUtil;
import com.windcloud.jwt.JwtUserDetailsService;
import com.windcloud.service.UserService;
import org.springframework.security.core.userdetails.UserDetails;

@org.springframework.web.bind.annotation.RestController
@RequestMapping("/api")
@CrossOrigin
public class RestController {

	
	@Autowired
	private UserService userService;
	
	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Autowired
	private JwtUserDetailsService userDetailsService;
	
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
	
	
	@RequestMapping(value = "/signin", method = RequestMethod.POST)
	public ResponseEntity<?> createAuthenticationToken(@RequestBody UserDTO authenticationRequest) throws Exception {

		authenticate(authenticationRequest.getEmail(), authenticationRequest.getPassword());
		final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getEmail());

		final String token = jwtTokenUtil.generateToken(userDetails);
		User user=userService.findbyEmailId(authenticationRequest.getEmail());
		Response<User> response=new Response<>();
		response.setStatus(CommanConstants.SUCCESS);
		response.setToken(token);
		response.setData(user);
		response.setMessage(CommanConstants.LOGIN_SUCCESS);
		return ResponseEntity.ok(response);
	}

	private void authenticate(String username, String password) throws Exception {
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
		} catch (DisabledException e) {
			throw new Exception("USER_DISABLED", e);
		} catch (BadCredentialsException e) {
			throw new Exception("INVALID_CREDENTIALS", e);
		}
	}
}

