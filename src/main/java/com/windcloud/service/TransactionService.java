package com.windcloud.service;

import org.springframework.http.ResponseEntity;

import com.windcloud.config.Response;
import com.windcloud.dto.DepositAmountDTO;

public interface TransactionService {
	public ResponseEntity<Response<?>>depositStorePoints(DepositAmountDTO depositDTO);
}
