package com.windcloud.dto;

import java.math.BigDecimal;

import javax.validation.constraints.NotEmpty;

import com.windcloud.entity.TransactionType;
import com.windcloud.entity.User;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class DepositAmountDTO 
{
	@NotEmpty
	private User user;
	
	@NotEmpty
	private User creator;
	
	@NotEmpty
	private BigDecimal amount;
	
	@NotEmpty
	private TransactionType transactionType;
}
