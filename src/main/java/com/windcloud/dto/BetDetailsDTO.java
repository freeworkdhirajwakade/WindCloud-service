package com.windcloud.dto;

import java.util.Set;

import com.windcloud.entity.Bet;
import com.windcloud.entity.BetType;
import com.windcloud.entity.Command;
import com.windcloud.entity.Game;
import com.windcloud.entity.Room;
import com.windcloud.entity.User;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BetDetailsDTO 
{

	private Long betDetId;
	
	private Float odds;
	
	private User user;
	
    private Set<Bet> bets;
	
	private Command commonds;
	
	private Game game;
	
	private BetType betType;
	
	private String status;
	
	private Room room;

}
