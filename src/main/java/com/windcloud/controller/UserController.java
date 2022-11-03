package com.windcloud.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.windcloud.dto.BetDTO;
import com.windcloud.dto.BetDetailsDTO;
import com.windcloud.dto.DepositAmountDTO;
import com.windcloud.dto.MessageDTO;
import com.windcloud.dto.UserDTO;
import com.windcloud.dto.WithdrowRequestDTO;
import com.windcloud.service.BetDetailsService;
import com.windcloud.service.GameService;
import com.windcloud.service.TransactionService;
import com.windcloud.service.UserService;

@org.springframework.web.bind.annotation.RestController
public class UserController {
	
	@Autowired
	private UserService userServices;
	
	@Autowired
	private BetDetailsService betDetailService;
	
	@Autowired
	private TransactionService transactionService;
	
	@Autowired
	private GameService gameService;
	
	@RequestMapping(value = {"/user","/user/avatar"}, method = RequestMethod.PUT)
	public ResponseEntity<?> updateUser(@RequestBody UserDTO userDto) throws Exception 
	{
		return userServices.updateUser(userDto);
	}
	
	@RequestMapping(value = {"/user/message"}, method = RequestMethod.PUT)
	public ResponseEntity<?> sendMessage(@RequestBody MessageDTO messageDTO) throws Exception 
	{
		return userServices.sendMessage(messageDTO);
	}
	
	@RequestMapping(value = {"/user/{emailId}"}, method = RequestMethod.GET)
	public ResponseEntity<?> getUserByEmailId(@PathVariable String emailId) throws Exception 
	{
		return userServices.getUserByEmailId(emailId);
	}
	
	@RequestMapping(value = "/user/withdrowRequest", method = RequestMethod.POST)	
	public ResponseEntity<?>withDrow(@RequestBody WithdrowRequestDTO withdrowDto)
	{
		return transactionService.createWithdrowTranasaction(withdrowDto);
	}
	
	@RequestMapping(value = "/game/active_game", method = RequestMethod.GET)	
	public ResponseEntity<?>getActiveGames()
	{
		return gameService.getActiveGames();
	}
	
	@RequestMapping(value = "/user/depositAmountRequest", method = RequestMethod.POST)	
	public ResponseEntity<?>deposit(@RequestBody DepositAmountDTO depositAmount)
	{
		return transactionService.depositAmountRequest(depositAmount);
	}
	
	@RequestMapping(value = "/user/bet_details", method = RequestMethod.POST)	
	public ResponseEntity<?>putBets(@RequestBody BetDetailsDTO betDetailsDTO)
	{
		return betDetailService.createBetDetails(betDetailsDTO);
	}
	
	@RequestMapping(value = "/user/bet_details/{userId}", method = RequestMethod.GET)	
	public ResponseEntity<?>put_bet(@PathVariable Long userId)
	{
		return betDetailService.getBetDetailsByUserId(userId);
	}

}
