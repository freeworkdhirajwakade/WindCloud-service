package com.windcloud.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.windcloud.repository.BetRepository;
import com.windcloud.repository.BetTypeRepository;
import com.windcloud.service.BetDetailsService;
@Service
public class BetDetailsServiceImpl implements BetDetailsService 
{
	
	@Autowired
	private BetRepository betRepository;
	
	@Autowired
	private BetTypeRepository betTypeRepository;

}
