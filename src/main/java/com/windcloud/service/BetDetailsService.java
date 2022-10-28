package com.windcloud.service;

import org.springframework.http.ResponseEntity;

import com.windcloud.dto.BetDTO;
import com.windcloud.dto.BetDetailsDTO;

public interface BetDetailsService {

	ResponseEntity<?> createBetDetails(BetDetailsDTO betDetailsDTO);

	ResponseEntity<?> putBet(BetDTO betDTO);

}
