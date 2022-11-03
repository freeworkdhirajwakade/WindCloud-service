package com.windcloud.controller;

import javax.annotation.security.RolesAllowed;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.windcloud.dto.ApproveTransactionDTO;
import com.windcloud.dto.BetDetailsDTO;
import com.windcloud.dto.GameDTO;
import com.windcloud.dto.RoomDTO;
import com.windcloud.service.BetDetailsService;
import com.windcloud.service.GameService;
import com.windcloud.service.RoomService;
import com.windcloud.service.TransactionService;
import com.windcloud.service.UserService;

@RestController
@RequestMapping("/admin")
public class AdminController {
	
	@Autowired
	private TransactionService transactionService;
	
	@Autowired
	private RoomService roomService;
	
	@Autowired
	private BetDetailsService betDetailsService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private GameService gameService;
	
	@GetMapping("/")
	@RolesAllowed("SUPER_ADMIN")
	public String welcome()
	{
		return "WelCome in Lottery Appplication";
	}
	
	@RequestMapping(value = "/approve_transaction", method = RequestMethod.POST)	
	public ResponseEntity<?>approveTransaction(@RequestBody ApproveTransactionDTO  approveDto)
	{
		return transactionService.approveTransaction(approveDto);
	}
	@RequestMapping(value = "/create_room", method = RequestMethod.POST)	
	public ResponseEntity<?>create_room(@RequestBody RoomDTO roomDTO)
	{
		return roomService.createRoom(roomDTO);
	}
	@RequestMapping(value = "/room/{roomNo}", method = RequestMethod.GET)	
	public ResponseEntity<?>getRoom(@PathVariable String roomNo)
	{
		return roomService.getRoom(roomNo);
	}
	@RequestMapping(value = "/user", method = RequestMethod.GET)	
	public ResponseEntity<?>getAllUser()
	{
		return userService.getAllUser();
	}
	@RequestMapping(value = "/create_bet_details", method = RequestMethod.POST)	
	public ResponseEntity<?>createBetDetails(@RequestBody BetDetailsDTO betDetailsDTO)
	{
		return betDetailsService.createBetDetails(betDetailsDTO);
	}
	@RequestMapping(value = "/create_game", method = RequestMethod.POST)	
	public ResponseEntity<?>createGame(@RequestBody GameDTO gameDTO)
	{
		return gameService.createGame(gameDTO);
	}
	@RequestMapping(value = "/game/{gameId}", method = RequestMethod.GET)	
	public ResponseEntity<?>getGame(@PathVariable Long gameId)
	{
		return gameService.getGame(gameId);
	}
	

}
