package com.windcloud.service.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;

import com.windcloud.config.Response;
import com.windcloud.constants.CommanConstants;
import com.windcloud.dto.BetDTO;
import com.windcloud.dto.BetDetailsDTO;
import com.windcloud.entity.Bet;
import com.windcloud.entity.BetDetails;
import com.windcloud.entity.Game;
import com.windcloud.entity.Ranks;
import com.windcloud.entity.Room;
import com.windcloud.entity.User;
import com.windcloud.repository.BetDetailsRepository;
import com.windcloud.repository.BetRepository;
import com.windcloud.repository.BetTypeRepository;
import com.windcloud.repository.UserRepository;
import com.windcloud.service.BetDetailsService;
import com.windcloud.service.GameService;
import com.windcloud.service.RoomService;

@Service
public class BetDetailsServiceImpl implements BetDetailsService {

	@Autowired
	private BetRepository betRepository;

	@Autowired
	private GameService gameService;

	@Autowired
	private RoomService roomService;

	@Autowired
	private BetDetailsRepository betDetailsRepository;

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RankServiceImpl rankServiceImpl;

	public BetDetails findBetDetailsById(Long betDetailsId)
	{
		Optional<BetDetails> betDetailsOPT=betDetailsRepository.findById(betDetailsId);
		return betDetailsOPT.isPresent()?betDetailsOPT.get():null;
		
	}

	@Override
	public ResponseEntity<?>  createBetDetails(BetDetailsDTO betDetailsDTO) 
	{
		Response<BetDetails> response = new Response<BetDetails>();
		if (betDetailsDTO.getUser()==null ||betDetailsDTO.getUser().getUserId()==null) {
			response.setStatus(CommanConstants.FAILED);
			response.setMessage(CommanConstants.USER_DETAILS_EMPTY);
			return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
		}
		Optional<User> userOPT = userRepository.findById(betDetailsDTO.getUser().getUserId());
		if(!userOPT.isPresent()) 
		{
			response.setStatus(CommanConstants.FAILED);
			response.setMessage(CommanConstants.INVALID_USER_NO);
			return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
		}
		
		if (betDetailsDTO.getBets()==null ||betDetailsDTO.getBets().size()<=0) {
			response.setStatus(CommanConstants.FAILED);
			response.setMessage(CommanConstants.BETS_EMPTY);
			return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
		}
		if(betDetailsDTO.getRoom()==null)
		{
			response.setStatus(CommanConstants.FAILED);
			response.setMessage(CommanConstants.ROOM_NO_EMPTY);
			return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
		}
		if(betDetailsDTO.getGame()==null||("".equals(betDetailsDTO.getGame().getGameId())))
		{
			response.setStatus(CommanConstants.FAILED);
			response.setMessage(CommanConstants.GAME_DETAILS_EMPTY);
			return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
		}
		Game game=gameService.findGameById(betDetailsDTO.getGame().getGameId());
		if(game==null)
		{
			response.setStatus(CommanConstants.FAILED);
			response.setMessage(CommanConstants.GAME_DETAILS_EMPTY);
			return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
		}
		
		if(CommanConstants.GAME_STATUS_CLOSED.equals(game.getStatus()))
		{
			response.setStatus(CommanConstants.FAILED);
			response.setMessage(CommanConstants.GAME_CLOSED);
			return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
		}
		Room room=roomService.findRoomByNo(betDetailsDTO.getRoom().getRoomNo());
		if(room==null)
		{
			response.setStatus(CommanConstants.FAILED);
			response.setMessage(CommanConstants.ROOM_DEAILS_EMPTY);
			return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
		}
		BetDetails bd=new BetDetails();
		bd.setRoom(room);
		bd.setGame(game);
		//bd.setCommonds(betDetailsDTO.getCommonds());
		bd.setUser(userOPT.get());
		bd.setStatus(CommanConstants.BET_STATUS_CREATED);		
		BetDetails betDetailsSaved=betDetailsRepository.saveAndFlush(bd);
				
		Set<Bet>bets=betDetailsDTO.getBets();
		Set<Bet>betsSaved=new HashSet<>(); 
		for(Bet bet: bets)
		{
			if(bet.getAmount()==null||bet.getAmount()==0)
			{
				throw new ResourceAccessException(CommanConstants.BET_AMOUNT_EMPTY);
			}
			if(bet.getCarNumber()==null||bet.getAmount()==0)
			{
				throw new ResourceAccessException(CommanConstants.CAR_NO_EMPTY);
			}
			if(bet.getRank()==null||bet.getRank().getRankId()==0)
			{
				throw new ResourceAccessException(CommanConstants.INVALID_RANK_ID);
			}
			Ranks rn=rankServiceImpl.findRankById(bet.getRank().getRankId());
			bet.setRank(rn);
			bet.setBetDetails(betDetailsSaved);
			Bet bt=betRepository.saveAndFlush(bet);
			betsSaved.add(bt);
		}
		betDetailsSaved.setBets(betsSaved);
		BetDetails betDetailsUpdated=betDetailsRepository.saveAndFlush(betDetailsSaved);
		
		if(betDetailsUpdated!=null)
		{
			response.setData(betDetailsUpdated);
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
	public List<BetDetails> findBetDetailsByGameId(Long gameID) {
		
		return betDetailsRepository.findBetDetailsByGameId(gameID);
	}
	@Override
	public Bet findBetById(Long betID) {
		
		Optional<Bet>betOpt= betRepository.findById(betID);
		return betOpt.isPresent()?betOpt.get():null;
	}
	
	@Override
	public List<Bet> findBetBybetDetailsId(Long betDetailsID) {
		
		return betRepository.findBetByBetDetailsId(betDetailsID);
	}
	
	@Override
	public Bet saveOrUpdateBet(Bet bet) {
		
		return betRepository.saveAndFlush(bet);
	}

	@Override
	public BetDetails saveOrUpdateBetDetails(BetDetails betDetails) {
		return betDetailsRepository.saveAndFlush(betDetails);
	}

	@Override
	public List<BetDetails> findBetDetailsByUserId(Long userId) {
		return betDetailsRepository.findBetDetailsByUserId(userId);
	}

	@Override
	public ResponseEntity<?> getBetDetailsByUserId(Long userId) {
		Response<List<BetDetails>> response = new Response<List<BetDetails>>();
		if (userId==null) {
			response.setStatus(CommanConstants.FAILED);
			response.setMessage(CommanConstants.USER_DETAILS_EMPTY);
			return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
		}
		Optional<User> userOPT = userRepository.findById(userId);
		if(!userOPT.isPresent()) 
		{
			response.setStatus(CommanConstants.FAILED);
			response.setMessage(CommanConstants.INVALID_USER_NO);
			return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
		}
		List<BetDetails>betDetails=betDetailsRepository.findBetDetailsByUserId(userId);
		if(betDetails!=null)
		{
			response.setData(betDetails);
			response.setStatus(CommanConstants.SUCCESS);
			response.setMessage(CommanConstants.SUCCESS_DATA_FOUND);
			return new ResponseEntity<>(response, HttpStatus.OK);
		}
		else
		{
			response.setStatus(CommanConstants.FAILED);
			response.setMessage(CommanConstants.DATA_NOT_FOUND);
			return new ResponseEntity<>(response, HttpStatus.OK);
		}
	}
}
