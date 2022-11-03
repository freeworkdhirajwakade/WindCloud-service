package com.windcloud.service.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.windcloud.entity.Game;
import com.windcloud.entity.RankDetails;
import com.windcloud.entity.Ranks;
import com.windcloud.repository.RankDetailsRepository;
import com.windcloud.repository.RankRepository;
import com.windcloud.service.GameService;
import com.windcloud.utils.Util;

@Service
public class RankServiceImpl {
	
	Logger logger=LoggerFactory.getLogger(getClass());
	
	@Autowired
	private RankRepository rankRepository;
	
	@Autowired
	private RankDetailsRepository rankDetailsRepository;
	
	@Autowired
	private GameService gameService;
	
	@SuppressWarnings("null")
	public Boolean createRankDetails(Game game)
	{
		logger.info("RankServiceImpl.createRankDetails-Started");
		try
		{
		Set<RankDetails>rankdetails=new HashSet<>();
		List<Ranks>ranks=rankRepository.findAll();
		int[] randomResult=Util.generateRank();
		for(int i=0;i<randomResult.length;i++)
		{
			
			Ranks rank=null;
			for(Ranks rk:ranks)
			{
				if(rk.getRankId()==(i+1))
				{
					rank=rk;
					break;
				}
			}
			RankDetails rnDetails=new RankDetails();
			rnDetails.setRankValue(randomResult[i]);
			rnDetails.setGame(game);
			rnDetails.setRank(rank);
			RankDetails rankDetailSave=rankDetailsRepository.saveAndFlush(rnDetails);
			rankdetails.add(rankDetailSave);
		}
		
		game.setRankdDetails(rankdetails);
		gameService.saveUpdateGame(game);
		logger.info("RankServiceImpl.createRankDetails-Success");
		return true;
		}
		catch (Exception e) {
			logger.error("RankServiceImpl.createRankDetails-error "+e.getMessage());
			return false;
		}
	}
	
	public List<RankDetails>findRankDetailsbyGameId(Long gameId)
	{
		return rankDetailsRepository.findRankDetailsByGameId(gameId);
		
	}
	
	public Ranks findRankById(Long rankId)
	{
		Optional<Ranks>rankOPT=rankRepository.findById(rankId);
		return rankOPT.isPresent()?rankOPT.get():null;
	}

}
