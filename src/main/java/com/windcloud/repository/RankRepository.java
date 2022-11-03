package com.windcloud.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.windcloud.entity.Ranks;
@Repository
public interface RankRepository extends JpaRepository<Ranks, Long> 
{

}
