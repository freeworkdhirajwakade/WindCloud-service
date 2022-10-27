package com.windcloud.service;

import org.springframework.http.ResponseEntity;

import com.windcloud.config.Response;
import com.windcloud.dto.ApproveTransactionDTO;
import com.windcloud.dto.DepositAmountDTO;
import com.windcloud.dto.WithdrowRequestDTO;

public interface TransactionService {
	public ResponseEntity<?> depositAmountRequest(DepositAmountDTO depositDTO);

	public ResponseEntity<?> createWithdrowTranasaction(WithdrowRequestDTO withdrowDto);
	
	public ResponseEntity<?> approveTransaction(ApproveTransactionDTO approveDto);
}
