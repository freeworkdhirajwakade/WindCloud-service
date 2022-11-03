package com.windcloud.scheduler;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import com.windcloud.constants.CommanConstants;
import com.windcloud.entity.Game;
import com.windcloud.exception.ResourceNotFoundException;
import com.windcloud.service.GameService;
import com.windcloud.service.ResultService;
import com.windcloud.utils.Util;


@Configuration
@EnableScheduling
public class SchedulerConfig {
	

	Logger logger=LoggerFactory.getLogger(getClass());
	
	@Autowired
	private GameService gameService;
	
	@Autowired
	private ResultService resultService;
		
	@Scheduled(fixedDelay = CommanConstants.GAME_CREATE_TIME_MIN,initialDelay = CommanConstants.GAME_CREATE_INITIAL_TIME_MIN)
	public void scheduleCreateNewGame() throws ResourceNotFoundException 
	{
		List<Game>games=gameService.findActiveGame();
		for(Game game:games)
		{
			resultService.generateGameResult(game);
			resultService.generateResult(game);
			logger.info("Generated Result for game ->"+game.getGameName());
		}
		gameService.closeOpenGame();
		Game game=gameService.createNewGame();
		logger.info("New Game Created ->"+game.getGameName());
		
	}
	
	/*@Scheduled(fixedDelay = CommanConstants.GAME_GENERATE_RESULT_TIME_MIN)
	public void scheduleGenerateResult() throws ResourceNotFoundException 
	{
		List<Game>games=gameService.findActiveGame();
		for(Game game:games)
		{
			resultService.generateGameResult(game);
			logger.info("Generated Result for game ->"+game.getGameName());
		}
		
	}
	
	
	@Scheduled(fixedDelay = CommanConstants.GAME_FETCH_TIME_MIN)
	public void scheduleStartGame() 
	{
		//List<Game> games=gameService.findGameActiveOpenTime();
		//games.forEach(game->{
			
			logger.info("Started game ->");
			
		//});	
	}
	*/

}
