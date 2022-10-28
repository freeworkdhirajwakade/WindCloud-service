package com.windcloud.service.impl;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.windcloud.config.Response;
import com.windcloud.constants.CommanConstants;
import com.windcloud.dto.BetDTO;
import com.windcloud.dto.BetDetailsDTO;
import com.windcloud.entity.Bet;
import com.windcloud.entity.BetDetails;
import com.windcloud.entity.User;
import com.windcloud.repository.BetDetailsRepository;
import com.windcloud.repository.BetRepository;
import com.windcloud.repository.BetTypeRepository;
import com.windcloud.repository.UserRepository;
import com.windcloud.service.BetDetailsService;
@Service
public class BetDetailsServiceImpl implements BetDetailsService 
{
	
	@Autowired
	private BetRepository betRepository;
	
	@Autowired
	private BetTypeRepository betTypeRepository;
	
	@Autowired
	private BetDetailsRepository betDetailsRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public ResponseEntity<?> createBetDetails(BetDetailsDTO betDetailsDTO) {
		
		Response<BetDetails> response = new Response<BetDetails>();

		if (betDetailsDTO.getBetType()==null || betDetailsDTO.getBetType().getBetTypeId()==null)
		{
			response.setStatus(CommanConstants.FAILED);
			response.setMessage(CommanConstants.BET_TYPE_EMPTY);
			return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
		}
		if(betDetailsDTO.getRoom()==null||betDetailsDTO.getRoom().getRoomNo()==null)
		{
			response.setStatus(CommanConstants.FAILED);
			response.setMessage(CommanConstants.ROOM_NO_EMPTY);
			return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
		}
		
		BetDetails betDetails=modelMapper.map(betDetailsDTO, BetDetails.class);
		BetDetails betDetailsSave=betDetailsRepository.save(betDetails);
		if(betDetailsSave!=null)
		{
			response.setData(betDetailsSave);
			response.setStatus(CommanConstants.SUCCESS);
			response.setMessage(CommanConstants.MSG_SUCCESS_SAVE);
			return new ResponseEntity<>(response, HttpStatus.OK);
		}
		else
		{
			response.setStatus(CommanConstants.FAILED);
			response.setMessage(CommanConstants.MSG_FAIED);
			return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
		}
	}

	@Override
	public ResponseEntity<?> putBet(BetDTO betDTO) 
	{
		Response response = new Response<User>();
		if (betDTO.getUser()==null ||betDTO.getUser().getUserId()==null) {
			response.setStatus(CommanConstants.FAILED);
			response.setMessage(CommanConstants.USER_DETAILS_EMPTY);
			return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
		}
		Optional<User> userOPT = userRepository.findById(betDTO.getUser().getUserId());
		if(!userOPT.isPresent()) 
		{
			response.setStatus(CommanConstants.FAILED);
			response.setMessage(CommanConstants.INVALID_USER_NO);
			return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
		}
		if (betDTO.getCarNumber()==null ||betDTO.getCarNumber().equals("")) {
			response.setStatus(CommanConstants.FAILED);
			response.setMessage(CommanConstants.CAR_NO_EMPTY);
			return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
		}
		if (betDTO.getAmount()==null) {
			response.setStatus(CommanConstants.FAILED);
			response.setMessage(CommanConstants.BET_AMOUNT_EMPTY);
			return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
		}
		if (betDTO.getBetDetailsDTO()==null||betDTO.getBetDetailsDTO().getBetDetId()==null) {
			response.setStatus(CommanConstants.FAILED);
			response.setMessage(CommanConstants.INVALID_BET_DETAILS);
			return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
		}
		Optional<BetDetails> betDetailOPT=betDetailsRepository.findById(betDTO.getBetDetailsDTO().getBetDetId());
		if(!betDetailOPT.isPresent()) 
		{
			response.setStatus(CommanConstants.FAILED);
			response.setMessage(CommanConstants.INVALID_BET_DETAILS);
			return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
		}
		Bet bet=modelMapper.map(betDTO,Bet.class);
		BetDetails betDetails=betDetailOPT.get();
		
		Bet betSave=betRepository.saveAndFlush(bet);
		betDetails.getBets().add(betSave);
		BetDetails betDetailsSave=betDetailsRepository.saveAndFlush(betDetails);
		betSave.setBetDetails(betDetailsSave);
		Bet betSave1=betRepository.saveAndFlush(betSave);
		if(betSave1!=null&&betDetailsSave!=null)
		{
			response.setData(betSave);
			response.setStatus(CommanConstants.SUCCESS);
			response.setMessage(CommanConstants.MSG_SUCCESS_SAVE);
			return new ResponseEntity<>(response, HttpStatus.OK);
		}
		else
		{
			response.setStatus(CommanConstants.FAILED);
			response.setMessage(CommanConstants.MSG_FAIED);
			return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
		}
	}

}
