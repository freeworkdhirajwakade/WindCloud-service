package com.windcloud.service.impl;

import java.math.BigDecimal;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.windcloud.WindCloudServiceApplication;
import com.windcloud.config.TransactionResponse;
import com.windcloud.constants.CommanConstants;
import com.windcloud.dto.ApproveTransactionDTO;
import com.windcloud.dto.DepositAmountDTO;
import com.windcloud.dto.WithdrowRequestDTO;
import com.windcloud.entity.Transaction;
import com.windcloud.entity.TransactionType;
import com.windcloud.entity.User;
import com.windcloud.exception.TransactionFailedException;
import com.windcloud.repository.TransactionRepository;
import com.windcloud.repository.TransactionTypeRepository;
import com.windcloud.service.TransactionService;
import com.windcloud.service.UserService;
import com.windcloud.utils.Util;
@Service
public class TransactionServiceImpl implements TransactionService
{
	public final static Logger logger = LoggerFactory.getLogger(WindCloudServiceApplication.class);
	
	@Autowired
	private TransactionRepository transactionRepository;
	
	@Autowired
	private TransactionTypeRepository transactionTypeRepository;
	
	@Autowired
	private UserService userService;
	
	@Transactional
	public ResponseEntity<?> depositAmountRequest(DepositAmountDTO depositDTO)
	{
		TransactionResponse<Transaction> response = new TransactionResponse<Transaction>();
		
		try
		{
			if(depositDTO.getUser()==null||depositDTO.getUser().getUserId()==null)
			{
				response.setStatus(CommanConstants.FAILED);
				response.setMessage(CommanConstants.USER_DETAILS_EMPTY);
				return new ResponseEntity<TransactionResponse<?>>(response, HttpStatus.BAD_REQUEST);
			}
			
			User user=userService.findUserById(depositDTO.getUser().getUserId());
			if(user==null)
			{
				response.setStatus(CommanConstants.FAILED);
				response.setMessage(CommanConstants.USER_DETAILS_EMPTY);
				return new ResponseEntity<TransactionResponse<?>>(response, HttpStatus.BAD_REQUEST);
			}
			
			if(depositDTO.getCreator()==null||depositDTO.getCreator().getUserId()==null)
			{
				response.setStatus(CommanConstants.FAILED);
				response.setMessage(CommanConstants.CREATOR_EMPTY);
				return new ResponseEntity<TransactionResponse<?>>(response, HttpStatus.BAD_REQUEST);
			}
			
			TransactionType type=transactionTypeRepository.findTransactionTypeByType("DEPOSIT");
			if(type==null)
			{
				response.setStatus(CommanConstants.FAILED);
				response.setMessage(CommanConstants.TRANSAC_TYPE_SHOULD_NOT_EMPTY);
				return new ResponseEntity<TransactionResponse<?>>(response, HttpStatus.BAD_REQUEST);
			}
			User creator=userService.findUserById(depositDTO.getCreator().getUserId());
			if(creator==null)
			{
				response.setStatus(CommanConstants.FAILED);
				response.setMessage(CommanConstants.CREATOR_INVALID);
				return new ResponseEntity<TransactionResponse<?>>(response, HttpStatus.BAD_REQUEST);
			}
			Transaction trx=new Transaction();
			trx.setAmount(depositDTO.getAmount());
			trx.setUser(user);
			trx.setCreator(creator);
			trx.setTranType(type);
			trx.setStatus(CommanConstants.TRX_STATUS_CREATED);
			trx.setCommand("");
			trx.setTranRefNo(Util.generateTransactionRef());
			trx.setFee(0.0);			
			Transaction trxSaved=transactionRepository.save(trx);
			if(trxSaved!=null)
			{
				response.setTranRefNo(trxSaved.getTranRefNo());
				response.setData(trxSaved);
				response.setStatus(CommanConstants.SUCCESS);
				response.setMessage(CommanConstants.MSG_SUCCESS_SAVE);
				return new ResponseEntity<>(response, HttpStatus.OK);
			}
			else
			{
				response.setStatus(CommanConstants.FAILED);
				response.setMessage(CommanConstants.MSG_FAIED);
				return new ResponseEntity<>(response, HttpStatus.BAD_GATEWAY);
			}
		}
		catch (Exception e) {
			response.setStatus(CommanConstants.FAILED);
			response.setMessage(CommanConstants.MSG_SUCCESS_SAVE);
			return new ResponseEntity<>(response, HttpStatus.BAD_GATEWAY);
		}
		
	}

	@Override
	@Transactional
	public ResponseEntity<?> createWithdrowTranasaction(WithdrowRequestDTO withdrowDto)
	{
		TransactionResponse<Transaction> response = new TransactionResponse<Transaction>();
		
		try
		{
			if(withdrowDto.getUser()==null||withdrowDto.getUser().getUserId()==null)
			{
				response.setStatus(CommanConstants.FAILED);
				response.setMessage(CommanConstants.USER_DETAILS_EMPTY);
				return new ResponseEntity<TransactionResponse<?>>(response, HttpStatus.BAD_REQUEST);
			}
			
			User user=userService.findUserById(withdrowDto.getUser().getUserId());
			if(user==null)
			{
				response.setStatus(CommanConstants.FAILED);
				response.setMessage(CommanConstants.INVALID_USER_NO);
				return new ResponseEntity<TransactionResponse<?>>(response, HttpStatus.BAD_REQUEST);
			}
			if(withdrowDto.getAmount().compareTo(user.getStorePoints()) > 0)
			{
				response.setStatus(CommanConstants.FAILED);
				response.setMessage(CommanConstants.INEFFICIENT_AMOUNT);
				return new ResponseEntity<TransactionResponse<?>>(response, HttpStatus.BAD_REQUEST);
			}
			
			if(withdrowDto.getCreator()==null||withdrowDto.getCreator().getUserId()==null)
			{
				response.setStatus(CommanConstants.FAILED);
				response.setMessage(CommanConstants.CREATOR_EMPTY);
				return new ResponseEntity<TransactionResponse<?>>(response, HttpStatus.BAD_REQUEST);
			}
			
			TransactionType type=transactionTypeRepository.findTransactionTypeByType("WITHDRAW");
			User creator=userService.findUserById(withdrowDto.getCreator().getUserId());
			if(creator==null)
			{
				response.setStatus(CommanConstants.FAILED);
				response.setMessage(CommanConstants.CREATOR_INVALID);
				return new ResponseEntity<TransactionResponse<?>>(response, HttpStatus.BAD_REQUEST);
			}
			Transaction trx=new Transaction();
			trx.setAmount(withdrowDto.getAmount());
			trx.setUser(user);
			trx.setCreator(creator);
			trx.setTranType(type);
			trx.setStatus(CommanConstants.TRX_STATUS_CREATED);
			trx.setCommand("");
			trx.setTranRefNo(Util.generateTransactionRef());
			trx.setFee(0.0);			
			Transaction trxSaved=transactionRepository.save(trx);
			
			if(trxSaved!=null)
			{
				response.setTranRefNo(trxSaved.getTranRefNo());
				response.setData(trxSaved);
				response.setStatus(CommanConstants.SUCCESS);
				response.setMessage(CommanConstants.MSG_SUCCESS_SAVE);
				return new ResponseEntity<>(response, HttpStatus.OK);
			}
			else
			{
				response.setStatus(CommanConstants.FAILED);
				response.setMessage(CommanConstants.MSG_FAIED);
				return new ResponseEntity<>(response, HttpStatus.BAD_GATEWAY);
			}
		}
		catch (Exception e) {
			response.setStatus(CommanConstants.FAILED);
			response.setMessage(CommanConstants.MSG_SUCCESS_SAVE);
			return new ResponseEntity<>(response, HttpStatus.BAD_GATEWAY);
		}
	}
	
	@Override
	@Transactional
	public ResponseEntity<?> approveTransaction(ApproveTransactionDTO approveDTO )
	{
		TransactionResponse<Transaction> response = new TransactionResponse<Transaction>();
		if(approveDTO.getTranRefNo()==null||"".equals(approveDTO.getTranRefNo()))
		{
			response.setStatus(CommanConstants.FAILED);
			response.setMessage(CommanConstants.INVALID_TRANSAC_REF);
			return new ResponseEntity<TransactionResponse<?>>(response, HttpStatus.BAD_REQUEST);
		}
		Transaction trx=transactionRepository.findTransactionByTranRefNo(approveDTO.getTranRefNo());
		if(trx==null)
		{
			response.setStatus(CommanConstants.FAILED);
			response.setMessage(CommanConstants.INVALID_TRANSAC_REF);
			return new ResponseEntity<TransactionResponse<?>>(response, HttpStatus.BAD_REQUEST);
		}
		Transaction transactionSave=approveTran(trx);
		if(transactionSave!=null)
		{
			response.setData(transactionSave);
			response.setTranRefNo(transactionSave.getTranRefNo());
			response.setStatus(CommanConstants.SUCCESS);
			response.setMessage(CommanConstants.TRANSACTION_APPROVEED);
			return new ResponseEntity<TransactionResponse<?>>(response, HttpStatus.OK);
		}
		else
		{
			response.setStatus(CommanConstants.FAILED);
			response.setMessage(CommanConstants.MSG_FAIED);
			return new ResponseEntity<TransactionResponse<?>>(response, HttpStatus.BAD_GATEWAY);
		}
	}
	
	@Transactional
	public Transaction approveTran(Transaction trx)
	{
		try
		{
			String trxType=trx.getTranType().getTranType();
			User user=userService.findUserById(trx.getUser().getUserId());
			BigDecimal storePoint=user.getStorePoints();
			switch (trxType) 
			{
				case "DEPOSIT":
					storePoint=storePoint.add(trx.getAmount());
					break;
				case "WITHDRAW":
					storePoint=storePoint.subtract(trx.getAmount());
					break;
				default:
					break;
			}
			user.setStorePoints(storePoint);
			User userSave=userService.saveOrUpdateUser(user);
			trx.setStatus(CommanConstants.TRANSACTION_APPROVEED);
			Transaction trxSave=transactionRepository.saveAndFlush(trx);
			if(userSave!=null&&trxSave!=null)
			{
				return trxSave;
			}
			else
			{
				throw new TransactionFailedException("Transactino Failed");
			}
		}
		catch (Exception e) {
			e.printStackTrace();
			logger.error("Transaction Failed =>"+e.getMessage());
			return null;
		}
		
	}
	
		
}
