package com.windcloud.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.windcloud.entity.Game;

@Repository
public interface GameRepository extends JpaRepository<Game,Long>
{

}
