package com.windcloud.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.windcloud.entity.TransactionType;
@Repository
public interface MessageTypeRepository extends JpaRepository<com.windcloud.entity.MessageType, Long> {


}
