package com.windcloud.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GameDTO 
{
	private Long gameId;
	
	private String gameName;
	
	private Boolean isGameOpen;
	
	private Long openTime;
	
	private Long closeTime;
	
	private Boolean schedulerTaskStatus;
	
	private BigDecimal bettingLimit;
	
	private Boolean isCancellationAllow;
	
	private String status;
	
    private LocalDateTime createDateTime;
 
    private LocalDateTime updateDateTime;

}
