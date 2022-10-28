package com.windcloud.service.impl;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.windcloud.config.Response;
import com.windcloud.constants.CommanConstants;
import com.windcloud.dto.GameDTO;
import com.windcloud.entity.BetDetails;
import com.windcloud.entity.Game;
import com.windcloud.repository.GameRepository;
import com.windcloud.service.GameService;

@Service
public class GameServiceImpl implements GameService
{
	@Autowired
	private GameRepository gameRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	public ResponseEntity<?> createGame(GameDTO gameDTO)
	{
		Response<Game> response = new Response<Game>();
		if(gameDTO.getGameName()==null||!"".equals(gameDTO.getGameName()))
		{
			response.setStatus(CommanConstants.FAILED);
			response.setMessage(CommanConstants.GAME_NAME_EMPTY);
			return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
		}
		if(gameDTO.getBettingLimit()==null)
		{
			response.setStatus(CommanConstants.FAILED);
			response.setMessage(CommanConstants.BETTING_LIMIT_EMPTY);
			return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
		}
		if(gameDTO.getOpenTime()==null)
		{
			response.setStatus(CommanConstants.FAILED);
			response.setMessage(CommanConstants.OPEN_TIME_EMPTY);
			return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
		}
		if(gameDTO.getCloseTime()==null)
		{
			response.setStatus(CommanConstants.FAILED);
			response.setMessage(CommanConstants.CLOSE_TIME_EMPTY);
			return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
		}
		
		Game game=modelMapper.map(gameDTO, Game.class);
		game.setStatus(CommanConstants.GAME_STATUS_ACTIVE);
		game.setSchedulerTaskStatus(CommanConstants.GAME_SCH_STATUS_ACTIVE);
		Game gameSave=saveUpdateGame(game);
		if(gameSave!=null)
		{
			response.setData(gameSave);
			response.setStatus(CommanConstants.SUCCESS);
			response.setMessage(CommanConstants.MSG_SUCCESS_SAVE);
			return new ResponseEntity<Response<?>>(response, HttpStatus.OK);
		}
		else
		{
			response.setStatus(CommanConstants.FAILED);
			response.setMessage(CommanConstants.MSG_FAIED);
			return new ResponseEntity<Response<?>>(response, HttpStatus.OK);
		}
		
	}
	
	public Game saveUpdateGame(Game game)
	{
		return gameRepository.saveAndFlush(game);
	}

	@Override
	public ResponseEntity<?> getAllGame() {
		
		Response<List<Game>> response = new Response<List<Game>>();
		List<Game>gameList=gameRepository.findAll();
		if(gameList!=null&&gameList.size()>0)
		{
			response.setData(gameList);
			response.setStatus(CommanConstants.SUCCESS);
			response.setMessage(CommanConstants.SUCCESS_DATA_FOUND);
			return new ResponseEntity<Response<?>>(response, HttpStatus.OK);
		}
		else
		{
			response.setStatus(CommanConstants.FAILED);
			response.setMessage(CommanConstants.DATA_NOT_FOUND);
			return new ResponseEntity<Response<?>>(response, HttpStatus.OK);
		}
	}

	@Override
	public ResponseEntity<?> getGame(Long gameId) {
		Response<Game> response = new Response<Game>();
		Optional<Game> gameOPT=gameRepository.findById(gameId);
		if(gameOPT.isPresent())
		{
			response.setData(gameOPT.get());
			response.setStatus(CommanConstants.SUCCESS);
			response.setMessage(CommanConstants.SUCCESS_DATA_FOUND);
			return new ResponseEntity<Response<?>>(response, HttpStatus.OK);
		}
		else
		{
			response.setStatus(CommanConstants.FAILED);
			response.setMessage(CommanConstants.DATA_NOT_FOUND);
			return new ResponseEntity<Response<?>>(response, HttpStatus.OK);
		}
	}
}