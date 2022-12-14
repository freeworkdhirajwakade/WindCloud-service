package com.windcloud.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.windcloud.config.Response;
import com.windcloud.constants.CommanConstants;
import com.windcloud.dto.ForgotPasswordDTO;
import com.windcloud.dto.UserDTO;
import com.windcloud.mail.MailService;
import com.windcloud.service.UserService;

@org.springframework.web.bind.annotation.RestController
@RequestMapping("/rest")
@CrossOrigin
public class RestController {

	
	@Autowired
	private UserService userService;
	
	@Autowired
	private MailService mailService;
	
	
	@PostMapping(value = "/signup")
	public ResponseEntity<?>signUpUser(@RequestBody UserDTO userDto)
	{
		return userService.signupUser(userDto);
	}
	
	@RequestMapping(value = "/signin", method = RequestMethod.POST)
	public ResponseEntity<?> signin(@RequestBody UserDTO authenticationRequest) throws Exception {

		return userService.signin(authenticationRequest);
	}
	
	@RequestMapping(value = "/sendOTPMailVerify/{emailId}", method = RequestMethod.GET)
	public ResponseEntity<?> verifyEmail(@PathVariable String emailId) throws Exception {
		Response<String> response=new Response<>();
		String otp=mailService.registerOtpEmail(emailId);
		response.setStatus(CommanConstants.SUCCESS);
		response.setMessage(CommanConstants.OTP_SEND_SUCCESS);
		response.setData(otp);
		return ResponseEntity.ok(response);
	}
	
	@RequestMapping(value = "/forgotPassword/{emailId}", method = RequestMethod.GET)
	public ResponseEntity<?> forgotPassword(@PathVariable String emailId) throws Exception 
	{
		return userService.forgotPassword(emailId);
	}
	@RequestMapping(value = "/updatePassword/{token}", method = RequestMethod.POST)
	public ResponseEntity<?> updatePassword(@RequestBody ForgotPasswordDTO forgotpass,@PathVariable String token) throws Exception 
	{
		return userService.updatePassword(forgotpass.getEmail(),forgotpass.getConfirmPassword(),token);
	}
	
	
}

