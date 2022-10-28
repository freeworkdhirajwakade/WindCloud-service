package com.windcloud.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.windcloud.entity.BetDetails;
@Repository
public interface BetDetailsRepository extends JpaRepository<BetDetails, Long> {

}
