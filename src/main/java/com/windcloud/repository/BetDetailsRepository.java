package com.windcloud.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.windcloud.entity.BetDetails;
@Repository
public interface BetDetailsRepository extends JpaRepository<BetDetails, Long> {

	
	@Query("SELECT b FROM BetDetails b WHERE b.game.gameId= ?1")
	List<BetDetails> findBetDetailsByGameId(Long gameId);
	
	@Query("SELECT b FROM BetDetails b WHERE b.user.userId= ?1")
	List<BetDetails> findBetDetailsByUserId(Long userId);
}
