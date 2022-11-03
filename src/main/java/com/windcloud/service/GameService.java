package com.windcloud.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.windcloud.dto.GameDTO;
import com.windcloud.entity.Game;

public interface GameService {
	
	public ResponseEntity<?> createGame(GameDTO gameDTO);
	public ResponseEntity<?> getAllGame();
	public Game saveUpdateGame(Game game);
	public Game findGameById(Long gameId);
	public Game createNewGame();
	public List<Game> findActiveGame();
	public List<Game> findClosedGame();
	public List<Game> findGameActiveOpenTime();
	public void closeOpenGame();
	ResponseEntity<?> getGame(Long gameId);
	public ResponseEntity<?> getActiveGames();

}
