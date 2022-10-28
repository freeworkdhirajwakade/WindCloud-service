package com.windcloud.config;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.util.FileCopyUtils;

import com.windcloud.entity.BetType;
import com.windcloud.entity.Command;
import com.windcloud.entity.MessageType;
import com.windcloud.entity.Roles;
import com.windcloud.entity.TransactionType;
import com.windcloud.repository.BetTypeRepository;
import com.windcloud.repository.CommandRepository;
import com.windcloud.repository.MessageTypeRepository;
import com.windcloud.repository.RolesRepository;
import com.windcloud.repository.TransactionTypeRepository; 
@Configuration
public class PreloadData implements CommandLineRunner
{
	 final Logger LOGGER = LoggerFactory.getLogger(getClass());
	
	 @Value("${wind.cloud.preload.data}")
	 private Boolean preLoadData;
	 
	 @Autowired
	 ResourceLoader resourceLoader;
	 
	 @Autowired
	 private RolesRepository roleRepository;
	 
	 @Autowired
	 private BetTypeRepository betTypeRepository;
	 
	 @Autowired
	 private TransactionTypeRepository transactionTypeRepository;
	 
	 @Autowired
	 private MessageTypeRepository messageTypeRepository;
	 
	 @Autowired
	 private CommandRepository commandRepository;
	 
	@Override
	public void run(String... args) throws Exception {
		Resource resource = resourceLoader.getResource("classpath:data.json");
		    InputStream inputStream = resource.getInputStream();
		    try {
		        byte[] bdata = FileCopyUtils.copyToByteArray(inputStream);
		        String data = new String(bdata, StandardCharsets.UTF_8);
		        if(preLoadData) 
		        {
			        insertRole(data);
			        insertBetType(data);
			        insertTransactionType(data);
			        insertMessageType(data);
			        insertCommandType(data);
		        }
		    } catch (IOException e) {
		      LOGGER.error("IOException", e);
		    }
	}
	
	public void insertRole(String data)
	{
		LOGGER.info("******Role Insertion Started*****");
		 JSONObject json= new JSONObject(data);
	     JSONArray rolesJSON=json.getJSONArray("roles");
	     List<Roles>roleList=roleRepository.findAll();
	     
		for(int i=0;i<rolesJSON.length();i++)
        {
        	JSONObject roleJson=rolesJSON.getJSONObject(i);
        	Roles r=null;
        	for(Roles role:roleList)
        	{
        		if(role.getRole().equals(roleJson.get("name")))
	        	{
		        	r=role;
		        	break;
	        	}
        	}
        	
        	if(r==null)
        	{
        		Roles roleNew=new Roles();
        		roleNew.setRoleId(roleJson.getLong("id"));
        		roleNew.setRole(roleJson.getString("name"));
		        Roles roleSave=roleRepository.save(roleNew);
		        LOGGER.info("Role Insert -> "+roleSave.getRole());
        	}
        }
		LOGGER.info("******Role Insertion Ended*****");
	}
	public void insertBetType(String data)
	{
		LOGGER.info("******Bet Type Insertion Started*****");
		 JSONObject json= new JSONObject(data);
	     JSONArray betTypeJSON=json.getJSONArray("bet_type");
	     List<BetType>betTypeList=betTypeRepository.findAll();
	     
		for(int i=0;i<betTypeJSON.length();i++)
        {
        	JSONObject roleJson=betTypeJSON.getJSONObject(i);
        	BetType bt=null;
        	for(BetType betType:betTypeList)
        	{
        		if(betType.getBetType().equals(roleJson.get("betType")))
	        	{
		        	bt=betType;
		        	break;
	        	}
        	}
        	
        	if(bt==null)
        	{
        		BetType betType=new BetType();
        		betType.setBetType(roleJson.getString("betType"));
        		betType.setBetTypeId(roleJson.getLong("betTypeId"));
        		betType.setBetTypeInChinese(roleJson.getString("betTypeInChina"));
        		betType.setDescription(roleJson.getString("description"));
        		
        		BetType betTypeSave=betTypeRepository.save(betType);
		        LOGGER.info("BetType Insert -> "+betTypeSave.getBetType());
        	}
        }
		LOGGER.info("******Bet Type Insertion Ended*****");
	}
	
	public void insertTransactionType(String data)
	{
		LOGGER.info("******Transaction Type Insertion Started*****");
		 JSONObject json= new JSONObject(data);
	     JSONArray tranTypeJSON=json.getJSONArray("transaction_type");
	     List<TransactionType>transTypeList=transactionTypeRepository.findAll();
	     
		for(int i=0;i<tranTypeJSON.length();i++)
        {
        	JSONObject transTypeJson=tranTypeJSON.getJSONObject(i);
        	TransactionType tt=null;
        	for(TransactionType traType:transTypeList)
        	{
        		if(traType.getTranType().equals(transTypeJson.get("tranType")))
	        	{
		        	tt=traType;
		        	break;
	        	}
        	}
        	
        	if(tt==null)
        	{
        		TransactionType transacType=new TransactionType();
        		transacType.setDescription(transTypeJson.getString("description"));
        		transacType.setTranType(transTypeJson.getString("tranType"));
        		transacType.setTranTypeId(transTypeJson.getLong("tranTypeId"));
        		TransactionType transacTypeSave=transactionTypeRepository.save(transacType);
		        LOGGER.info("Transaction Type Insert -> "+transacTypeSave.getTranType());
        	}
        }
		LOGGER.info("******Transaction Type Insertion Ended*****");
	}
	
	public void insertMessageType(String data)
	{
		LOGGER.info("******Message Type Insertion Started*****");
		 JSONObject json= new JSONObject(data);
	     JSONArray msgTypeJSON=json.getJSONArray("message_type");
	     List<MessageType>msgTypeList=messageTypeRepository.findAll();
	     
		for(int i=0;i<msgTypeJSON.length();i++)
        {
        	JSONObject messTypeJson=msgTypeJSON.getJSONObject(i);
        	MessageType msgType=null;
        	for(MessageType msTy:msgTypeList)
        	{
        		if(msTy.getMsgType().equals(messTypeJson.get("msgType")))
	        	{
        			msgType=msTy;
		        	break;
	        	}
        	}
        	
        	if(msgType==null)
        	{
        		MessageType messageType=new MessageType();
        		messageType.setMsgType(messTypeJson.getString("msgType"));
        		messageType.setMsgTypeId(messTypeJson.getLong("msgTypeId"));
        		messageType.setDescription(messTypeJson.getString("description"));
        		MessageType msgTypeSave=messageTypeRepository.save(messageType);
		        LOGGER.info("Transaction Type Insert -> "+msgTypeSave.getMsgType());
        	}
        }
		LOGGER.info("******Message Type Insertion Ended*****");
	}
	
	public void insertCommandType(String data)
	{
		LOGGER.info("******User Command Type Insertion Started*****");
		 JSONObject json= new JSONObject(data);
	     JSONArray ucTypeJSON=json.getJSONArray("command_names");
	     List<Command>uCTypeList=commandRepository.findAll();
	     
		for(int i=0;i<ucTypeJSON.length();i++)
        {
        	JSONObject userCTypeJson=ucTypeJSON.getJSONObject(i);
        	Command uc=null;
        	for(Command ucTy:uCTypeList)
        	{
        		if(ucTy.getCommandName().equals(userCTypeJson.get("commandName")))
	        	{
        			uc=ucTy;
		        	break;
	        	}
        	}
        	
        	if(uc==null)
        	{
        		Command userCType=new Command();
        		userCType.setCommandName(userCTypeJson.getString("commandName"));
        		userCType.setCommandId(userCTypeJson.getLong("commandId"));
        		userCType.setDescription(userCTypeJson.getString("description"));
        		userCType.setCommandNameChinise(userCTypeJson.getString("commandNameChinise"));
        		Command ucTypeSave=commandRepository.save(userCType);
		        LOGGER.info("Transaction Type Insert -> "+ucTypeSave.getCommandName());
        	}
        }
		LOGGER.info("******User Command Type Insertion Ended*****");
	}
}
