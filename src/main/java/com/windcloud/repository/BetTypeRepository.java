package com.windcloud.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface BetTypeRepository extends JpaRepository<com.windcloud.entity.BetType, Long> {

}
