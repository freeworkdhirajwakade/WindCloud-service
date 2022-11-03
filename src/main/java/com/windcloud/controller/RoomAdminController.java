package com.windcloud.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.windcloud.config.Response;
import com.windcloud.constants.CommanConstants;
import com.windcloud.dto.DepositAmountDTO;
import com.windcloud.dto.ResultDTO;
import com.windcloud.dto.RoomDTO;
import com.windcloud.entity.Result;
import com.windcloud.exception.ResourceNotFoundException;
import com.windcloud.service.GameService;
import com.windcloud.service.ResultService;
import com.windcloud.service.RoomService;
import com.windcloud.service.TransactionService;
import com.windcloud.service.UserService;

@org.springframework.web.bind.annotation.RestController
@RequestMapping("/room_admin")
@CrossOrigin
public class RoomAdminController {
	
	@Autowired
	private ModelMapper mapper;
	
	@Autowired
	private TransactionService transactionService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private RoomService roomService;
	
	@Autowired
	private ResultService resultService;
	
	@Autowired
	private GameService gameService;
	
	@RequestMapping(value = "/hi", method = RequestMethod.GET)	
	public String sayhello()
	{
		return "Welcome Room Administrator";
	}
	
	@RequestMapping(value = {"/approve_user/{emailId}"}, method = RequestMethod.GET)
	public ResponseEntity<?> approve_user(@PathVariable String emailId) throws Exception 
	{
		return userService.approve_user(emailId);
	}
	
	@RequestMapping(value = {"/add_user_room"}, method = RequestMethod.POST)
	public ResponseEntity<?> add_user_room(@PathVariable String emailId) throws Exception 
	{
		return userService.approve_user(emailId);
	}

	@RequestMapping(value = "/result", method = RequestMethod.POST)	
	public ResponseEntity<?>generateResult(@RequestBody ResultDTO resultDTO)
	{
		Response<Result>response=new Response<Result>();
		Result result=mapper.map(resultDTO,Result.class);
		try {
			//resultService.generateGameResult(result);
			response.setStatus(CommanConstants.SUCCESS);
			response.setMessage(CommanConstants.MSG_SUCCESS_SAVE);
			return new ResponseEntity<Response<?>>(response, HttpStatus.OK);
		} 
		catch (Exception e) {
			response.setStatus(CommanConstants.FAILED);
			response.setMessage(CommanConstants.MSG_FAIED);
			return new ResponseEntity<Response<?>>(response, HttpStatus.BAD_REQUEST);
		}
	}
	
	@RequestMapping(value = "/result/{resultId}", method = RequestMethod.GET)	
	public ResponseEntity<?>generateResult(@PathVariable Long resultId)
	{
		Response<Result>response=new Response<Result>();
		try 
		{
				Result result=resultService.getResult(resultId);
				if(result!=null)
				{
				response.setStatus(CommanConstants.SUCCESS);
				response.setMessage(CommanConstants.MSG_SUCCESS_SAVE);
				return new ResponseEntity<Response<?>>(response, HttpStatus.OK);
				}
				else
				{
					response.setStatus(CommanConstants.FAILED);
					response.setMessage(CommanConstants.DATA_NOT_FOUND);
					return new ResponseEntity<Response<?>>(response, HttpStatus.OK);
				}
		} catch (Exception e) {
			response.setStatus(CommanConstants.FAILED);
			response.setMessage(CommanConstants.MSG_FAIED);
			return new ResponseEntity<Response<?>>(response, HttpStatus.BAD_REQUEST);
		}
	}
	

}
