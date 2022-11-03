package com.windcloud.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.windcloud.dto.BetDetailsDTO;
import com.windcloud.entity.Bet;
import com.windcloud.entity.BetDetails;

public interface BetDetailsService {

	public ResponseEntity<?> createBetDetails(BetDetailsDTO betDetailsDTO);

	public BetDetails findBetDetailsById(Long betDetailsId);
	
	List<BetDetails> findBetDetailsByGameId(Long gameID);

	List<Bet> findBetBybetDetailsId(Long betDetailsID);

	Bet saveOrUpdateBet(Bet bet);

	public BetDetails saveOrUpdateBetDetails(BetDetails betDe);
	
	public List<BetDetails>findBetDetailsByUserId(Long userId);
	
	public ResponseEntity<?>getBetDetailsByUserId(Long userId);

	Bet findBetById(Long betID);
}
