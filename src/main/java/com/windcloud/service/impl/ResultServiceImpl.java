package com.windcloud.service.impl;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.windcloud.constants.CommanConstants;
import com.windcloud.entity.Bet;
import com.windcloud.entity.BetDetails;
import com.windcloud.entity.Game;
import com.windcloud.entity.RankDetails;
import com.windcloud.entity.Result;
import com.windcloud.entity.User;
import com.windcloud.exception.ResourceNotFoundException;
import com.windcloud.repository.ResultRepository;
import com.windcloud.service.BetDetailsService;
import com.windcloud.service.GameService;
import com.windcloud.service.ResultService;
import com.windcloud.service.UserService;
@Service
public class ResultServiceImpl implements ResultService 
{	
	@Autowired
	private BetDetailsService betDetailsService;
	
	@Autowired
	private GameService gameService;
	
	@Autowired
	private RankServiceImpl rankService;
	
	@Autowired
	private ResultRepository resultRepository;
	
	@Autowired
	private UserService userService;
	
	Logger logger=LoggerFactory.getLogger(getClass());
			
	@Override
	public Result getResult(Long resultId) {
		return null;
	}

	@Override
	public Boolean generateGameResult(Game game) throws ResourceNotFoundException 
	{
		try
		{
			if(game==null)
			{
				throw new ResourceNotFoundException("Invalid Game Details");
			}
			return rankService.createRankDetails(game);
		}
		catch (Exception e) {
			return false;
		}
	}
	
	@Override
	public Boolean generateResult(Game game) throws ResourceNotFoundException 
	{
		try
		{
			if(game==null)
			{
				throw new ResourceNotFoundException("Invalid Game Details");
			}
			game=gameService.findGameById(game.getGameId());
			
			List<BetDetails>betDetailsSet=betDetailsService.findBetDetailsByGameId(game.getGameId());
			List<RankDetails>rankDetails=rankService.findRankDetailsbyGameId(game.getGameId());
						
			for(BetDetails betDe:betDetailsSet)
			{
				logger.info("ResultServiceImpl->generateResult ==>BetDetails ID="+betDe.getBetDetId());
				User user=userService.findUserById(betDe.getUser().getUserId());
				
				List<Bet>betsList=betDetailsService.findBetBybetDetailsId(betDe.getBetDetId());
				for(Bet bet:betsList)
				{
					Boolean win=true;
					logger.info("ResultServiceImpl->generateResult ==>Bet Id ="+bet.getBetId());
					for(RankDetails rankD:rankDetails)
					{
						if(bet.getCarNumber()==rankD.getRankValue() &&rankD.getRank().getRankId()==bet.getRank().getRankId())
						{
							logger.info("ResultServiceImpl->generateResult ==>+"+user.getUserId()+" Win Bet Id ="+bet.getBetId());
							Bet winBet=winBet(bet);
							
							BigDecimal point=user.getStorePoints().add(new BigDecimal(winBet.getWinAmount()));
							user.setStorePoints(point);
							userService.saveOrUpdateUser(user);
						
							win=false;
							break;
						}
					}
					
					if(win)
					{
						logger.info("ResultServiceImpl->generateResult ==>+"+user.getUserId()+" Loss Bet Id ="+bet.getBetId());
						
						Bet lossBet=lossBet(bet);
						BigDecimal point=user.getStorePoints().subtract(new BigDecimal(lossBet.getAmount()));
						user.setStorePoints(point);
						userService.saveOrUpdateUser(user);
						
					}			
					
				}
				
				betDe.setStatus(CommanConstants.BET_STATUS_COMPLETED);
				betDetailsService.saveOrUpdateBetDetails(betDe);
				logger.info("ResultServiceImpl->generateResult ==>success= BetDetails ID="+betDe.getBetDetId());
			}
			return true;
		}
		catch (Exception e) {
			
			logger.error("ResultServiceImpl->generateResult ==>error= "+e.getMessage());
			return false;
		}
	}
	
	
	public Bet winBet(Bet bet)
	{
		Bet betWin=betDetailsService.findBetById(bet.getBetId());
		betWin.setWinAmount(bet.getOdds()*bet.getAmount());
		betWin.setWinOrLoss(CommanConstants.BET_STATUS_WIN);
		betWin.setStatus(CommanConstants.BET_STATUS_COMPLETED);
		return betDetailsService.saveOrUpdateBet(betWin);
	}
	public Bet lossBet(Bet bet)
	{
		Bet betLoss=betDetailsService.findBetById(bet.getBetId());
		betLoss.setWinAmount(0.0);
		betLoss.setWinOrLoss(CommanConstants.BET_STATUS_LOSS);
		betLoss.setStatus(CommanConstants.BET_STATUS_COMPLETED);
		return betDetailsService.saveOrUpdateBet(betLoss);
	}
	
	

}
