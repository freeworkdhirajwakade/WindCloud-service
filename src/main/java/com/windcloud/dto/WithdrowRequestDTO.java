package com.windcloud.dto;

import java.math.BigDecimal;

import com.windcloud.entity.User;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class WithdrowRequestDTO 
{
	private User user;
	
	private User creator;
	
	private BigDecimal amount;
}
