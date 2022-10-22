package com.windcloud.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.windcloud.config.Response;
import com.windcloud.constants.CommanConstants;
import com.windcloud.dto.UserDTO;
import com.windcloud.entity.Roles;
import com.windcloud.entity.User;
import com.windcloud.jwt.JwtTokenUtil;
import com.windcloud.jwt.JwtUserDetailsService;
import com.windcloud.mail.MailService;
import com.windcloud.repository.RolesRepository;
import com.windcloud.repository.UserRepository;
import com.windcloud.service.UserService;
import com.windcloud.utils.Util;

import java.time.LocalDateTime;
import java.util.HashSet;

import org.modelmapper.ModelMapper;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RolesRepository roleRepository;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Autowired
	private JwtUserDetailsService userDetailsService;
	
	@Autowired
	private MailService mailService;

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public ResponseEntity<Response> signupUser(UserDTO userDto) {
		Response response = new Response<User>();

		if (userDto.getEmail().equals("") || userDto.getEmail() == null) {
			response.setStatus(CommanConstants.FAILED);
			response.setMessage(CommanConstants.EMAIL_EMPTY);
			return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
		}

		User userexist = userRepository.findbyEmailId(userDto.getEmail());
		if (userexist != null) {
			response.setStatus(CommanConstants.FAILED);
			response.setMessage(CommanConstants.USER_ALREADY_EXIST);
			return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
		}
		try {
			User user = modelMapper.map(userDto, User.class);
			user.setStatus(CommanConstants.CRAETED);
			HashSet<Roles> roles = new HashSet<>();
			if(user.getRoles()==null||user.getRoles().size()<0) 
			{
				Roles role = roleRepository.findRolebyRole("USER");
				roles.add(role);
			}else
			{
				user.getRoles().forEach(role->{
					Roles role1 = roleRepository.findRolebyRole(role.getRole());				
					roles.add(role1);
				});
			}
			user.setRoles(roles);
			user = userRepository.save(user);
			response.setData(user);
			response.setStatus(CommanConstants.SUCCESS);
			response.setMessage(CommanConstants.MSG_SUCCESS_SAVE);
			return new ResponseEntity<>(response, HttpStatus.OK);
		} catch (Exception e) {
			response.setData(null);
			response.setStatus(CommanConstants.FAILED);
			response.setMessage(CommanConstants.MSG_FAIED);
			return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
		}
	}

	public User findbyEmailId(String user) {
		return userRepository.findbyEmailId(user);
	}

	public ResponseEntity<Response> signin(UserDTO authenticationRequest) throws Exception 
	{
		authenticate(authenticationRequest.getEmail(), authenticationRequest.getPassword());
		final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getEmail());

		final String token = jwtTokenUtil.generateToken(userDetails);
		User user = findbyEmailId(authenticationRequest.getEmail());
		Response<User> response = new Response<>();
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

	public ResponseEntity<Response> forgotPassword(String emailId ) throws Exception 
	{
		Response<User> response = new Response<>();
		User user = findbyEmailId(emailId);
		if(user==null)
		{
			response.setStatus(CommanConstants.FAILED);
			response.setMessage(CommanConstants.EMAIL_ID_NOT_EXIST);
		}
		else
		{
			mailService.sendForgotPasswordLink(emailId);
			response.setStatus(CommanConstants.SUCCESS);
			response.setMessage(CommanConstants.FORGOT_PASS_MSG);
			return ResponseEntity.ok(response);
		}
		
		return ResponseEntity.ok(response);
		
	}

	@Override
	public ResponseEntity<?> updatePassword(String email, String confirmPassword,String token) {
		Response<User> response = new Response<>();
		try 
		{
			if(token==null)
			{
				response.setStatus(CommanConstants.FAILED);
				response.setMessage(CommanConstants.INVALID_URL);
				return ResponseEntity.ok(response);
			}
			else
			{
				String tokenExpTime=Util.decode(token);
				System.out.println("TOKEN DATE="+tokenExpTime);
				LocalDateTime pastDate = LocalDateTime.parse(tokenExpTime);
				boolean isBefore = LocalDateTime.now().isBefore(pastDate);
				if(isBefore)
				{
					User user = findbyEmailId(email);
					user.setPassword(confirmPassword);
					userRepository.saveAndFlush(user);
					response.setStatus(CommanConstants.SUCCESS);
					response.setMessage(CommanConstants.SUCCESS_FORGOTTED);
					return ResponseEntity.ok(response);
				}
				else
				{
					response.setStatus(CommanConstants.FAILED);
					response.setMessage(CommanConstants.FORGOT_PASS_LINK_EXPIRED);
					return ResponseEntity.ok(response);
				}
			}
		}
		catch (Exception e) {
			response.setStatus(CommanConstants.FAILED);
			response.setMessage(CommanConstants.MSG_FAIED);
			return ResponseEntity.ok(response);
		}
	}
	
}