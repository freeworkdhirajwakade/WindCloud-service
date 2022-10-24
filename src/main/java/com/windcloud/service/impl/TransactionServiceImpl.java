package com.windcloud.service.impl;

import java.math.BigDecimal;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.windcloud.config.Response;
import com.windcloud.constants.CommanConstants;
import com.windcloud.dto.DepositAmountDTO;
import com.windcloud.entity.Transaction;
import com.windcloud.entity.TransactionType;
import com.windcloud.entity.User;
import com.windcloud.repository.TransactionRepository;
import com.windcloud.repository.TransactionTypeRepository;
import com.windcloud.service.TransactionService;
import com.windcloud.service.UserService;
import com.windcloud.utils.Util;
@Service
public class TransactionServiceImpl implements TransactionService
{
	@Autowired
	private TransactionRepository transactionRepository;
	
	@Autowired
	private TransactionTypeRepository transactionTypeRepository;
	
	@Autowired
	private UserService userService;
	
	@Transactional
	public ResponseEntity<Response<?>>depositStorePoints(DepositAmountDTO depositDTO)
	{
		Response<?> response = new Response<TransactionType>();
		
		try
		{
			if(depositDTO.getUser()==null||depositDTO.getUser().getUserId()==null)
			{
				response.setStatus(CommanConstants.FAILED);
				response.setMessage(CommanConstants.USER_DETAILS_EMPTY);
				return new ResponseEntity<Response<?>>(response, HttpStatus.BAD_REQUEST);
			}
			
			User user=userService.findUserById(depositDTO.getUser().getUserId());
			if(user==null)
			{
				response.setStatus(CommanConstants.FAILED);
				response.setMessage(CommanConstants.USER_DETAILS_EMPTY);
				return new ResponseEntity<Response<?>>(response, HttpStatus.BAD_REQUEST);
			}
			
			if(depositDTO.getCreator()==null||depositDTO.getCreator().getUserId()==null)
			{
				response.setStatus(CommanConstants.FAILED);
				response.setMessage(CommanConstants.CREATOR_EMPTY);
				return new ResponseEntity<Response<?>>(response, HttpStatus.BAD_REQUEST);
			}
			if(depositDTO.getTransactionType()==null||depositDTO.getTransactionType().getTranType()=="")
			{
				response.setStatus(CommanConstants.FAILED);
				response.setMessage(CommanConstants.TRANSAC_TYPE_SHOULD_NOT_EMPTY);
				return new ResponseEntity<Response<?>>(response, HttpStatus.BAD_REQUEST);
			}
			TransactionType type=transactionTypeRepository.findTransactionTypeByType(depositDTO.getTransactionType().getTranType());
			if(type==null)
			{
				response.setStatus(CommanConstants.FAILED);
				response.setMessage(CommanConstants.TRANSAC_TYPE_SHOULD_NOT_EMPTY);
				return new ResponseEntity<Response<?>>(response, HttpStatus.BAD_REQUEST);
			}
			User creator=userService.findUserById(depositDTO.getCreator().getUserId());
			if(creator==null)
			{
				response.setStatus(CommanConstants.FAILED);
				response.setMessage(CommanConstants.CREATOR_INVALID);
				return new ResponseEntity<Response<?>>(response, HttpStatus.BAD_REQUEST);
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
			BigDecimal storePoint=user.getStorePoints();
			storePoint=storePoint.add(depositDTO.getAmount());
			System.out.println("Share Point=>"+storePoint);
			user.setStorePoints(storePoint);
			User userSave=userService.saveOrUpdateUser(user);
			
			if(trxSaved!=null&&userSave!=null)
			{
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
}
