package com.windcloud.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.windcloud.entity.Bet;
@Repository
public interface BetRepository extends JpaRepository<com.windcloud.entity.Bet, Long> {
	
	@Query("SELECT b FROM Bet b WHERE b.status = ?1")
	List<Bet> findBetByStatus(String status);
	
	
	@Query("SELECT b FROM Bet b WHERE b.betDetails.betDetId= ?1")
	List<Bet> findBetByBetDetailsId(Long betDetailsId);

}
