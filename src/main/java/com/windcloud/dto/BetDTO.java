package com.windcloud.dto;

import java.math.BigDecimal;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import com.windcloud.entity.BetDetails;
import com.windcloud.entity.BetType;
import com.windcloud.entity.User;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BetDTO
{
	private Long betTypeId;
	
	private User user;
	
	private BetDetailsDTO betDetailsDTO;
	
	private String carNumber;
	
	private BigDecimal amount;
	
	private String status;
}
