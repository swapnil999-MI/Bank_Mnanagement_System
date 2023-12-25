package com.example.demo.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.dto.BankAccountdto;
import com.example.demo.dto.TransactionHistory;

@Repository
public interface transactionhistoryRepository extends JpaRepository<TransactionHistory, Long> {
    // Define custom query methods if needed
	 List<TransactionHistory> findAll();
}