package com.windcloud.dto;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;

import com.windcloud.entity.Bet;
import com.windcloud.entity.BetType;
import com.windcloud.entity.Command;
import com.windcloud.entity.Room;

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
	
    private Set<Bet> bets = new HashSet<>(); 
	
	private Command commonds;
	
	private BetType betType;
	
	private String status;
	
	private Room room;

}
