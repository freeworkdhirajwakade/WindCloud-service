package com.windcloud.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.windcloud.entity.Game;
import com.windcloud.entity.RankDetails;
@Repository
public interface RankDetailsRepository extends JpaRepository<RankDetails, Long> {

	
	@Query("SELECT rk FROM RankDetails rk WHERE rk.game.gameId=?1")
	List<RankDetails> findRankDetailsByGameId(Long gameId);
}
