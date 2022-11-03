package com.windcloud.service;

import com.windcloud.entity.Game;
import com.windcloud.entity.Result;
import com.windcloud.exception.ResourceNotFoundException;

public interface ResultService {
	

	public Result getResult(Long resultId);

	Boolean generateGameResult(Game game) throws ResourceNotFoundException;

	Boolean generateResult(Game game) throws ResourceNotFoundException;

}
