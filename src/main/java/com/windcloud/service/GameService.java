package com.windcloud.service;

import org.springframework.http.ResponseEntity;

import com.windcloud.dto.GameDTO;
import com.windcloud.entity.Game;

public interface GameService {
	
	public ResponseEntity<?> createGame(GameDTO gameDTO);
	public ResponseEntity<?> getAllGame();
	public ResponseEntity<?> getGame(Long gameId);
	public Game saveUpdateGame(Game game);

}
