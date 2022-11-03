package com.windcloud.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.windcloud.entity.Bet;
import com.windcloud.entity.Game;
import com.windcloud.entity.User;

@Repository
public interface GameRepository extends JpaRepository<Game,Long>
{
	@Query(value = "SELECT count(*) FROM Game gm")
	Long findCount();
	
	@Query("SELECT g FROM Game g WHERE g.status = ?1")
	List<Game> findGameByStatus(String status);
	
	
	@Query("SELECT g FROM Game g WHERE g.gameId=?1 and g.status = ?2")
	List<Game> findGameByIdAndStatus(Long gameId,String status);
	
	@Query("SELECT g FROM Game g WHERE g.status ='Active' and g.openTime>=?1")
	List<Game> findGameActiveOpenTime(Long opentime);
	
}
