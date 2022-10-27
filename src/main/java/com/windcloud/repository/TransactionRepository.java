package com.windcloud.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.windcloud.entity.Transaction;
@Repository
public interface TransactionRepository extends JpaRepository<com.windcloud.entity.Transaction, Long> {

	@Query("SELECT t FROM Transaction t WHERE t.tranRefNo = ?1")
	Transaction findTransactionByTranRefNo(String tranRefNo);

}
