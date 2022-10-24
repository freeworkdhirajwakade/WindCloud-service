package com.windcloud.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.windcloud.entity.TransactionType;
@Repository
public interface TransactionTypeRepository extends JpaRepository<com.windcloud.entity.TransactionType, Long> {

	@Query("SELECT tt FROM TransactionType tt WHERE tt.tranType = ?1")
	TransactionType findTransactionTypeByType(String transactionType);

	
}
