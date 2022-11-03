package com.windcloud.dto;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import com.windcloud.entity.Game;
import com.windcloud.entity.Room;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ResultDTO 
{
	private Long resultId;
	
	private Integer carNumber;
	
	private Room room;
	
	private Game game;
	
	private Long createdAt;
	
	private Long finishedAt;
}
