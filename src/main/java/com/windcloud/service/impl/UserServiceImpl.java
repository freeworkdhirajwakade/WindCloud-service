package com.windcloud.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.windcloud.config.Response;
import com.windcloud.constants.CommanConstants;
import com.windcloud.dto.UserDTO;
import com.windcloud.entity.User;
import com.windcloud.repository.UserRepository;
import com.windcloud.service.UserService;
import org.modelmapper.ModelMapper;
@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private UserRepository userRepository;
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public ResponseEntity<Response>signupUser(UserDTO userDto)
	{
		Response response=new Response<User>();
		User user=modelMapper.map(userDto,User.class);
		user.setStatus(CommanConstants.CRAETED);
		user=userRepository.save(user);
		response.setResults(user);
		response.setStatus(CommanConstants.SUCCESS);
		response.setMessage(CommanConstants.MSG_SUCCESS_SAVE);
		return new ResponseEntity<>(response,HttpStatus.OK);
	}
		

}
