package com.example.demo.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

import com.example.demo.dto.Admindto;
import com.example.demo.dto.BankAccountdto;

@Repository
public interface BankAccountRepository extends JpaRepository<BankAccountdto, Long> {
	@Query("SELECT a FROM BankAccountdto a WHERE a.account_no = :accountno")
	BankAccountdto findByAccountNo( String accountno);
	//BankAccountdto findByaccount_no(Long account_no);
	
}