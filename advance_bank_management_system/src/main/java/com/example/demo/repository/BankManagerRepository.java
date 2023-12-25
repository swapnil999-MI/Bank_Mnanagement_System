package com.example.demo.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import com.example.demo.dto.BankManagerdto;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface BankManagerRepository extends JpaRepository<BankManagerdto, Long> {
     // Define custom query methods if needed
    default void saveBankManager(BankManagerdto bankManager) {
	  bankManager.setStatus("Unapproved");
	  save(bankManager);
	 }
        BankManagerdto findBygmail(String mail);
	    
	    @Transactional
	    @Modifying
	    @Query("UPDATE Customerdto c SET c.status = 'Approved', c.bankmanager = ?1 WHERE c.status = 'Unapproved'")
	    void approveAllUnapprovedCustomersByBankManager(BankManagerdto bankmanager,Long bankManagerId);

	    @Transactional
	    @Modifying
	    @Query("UPDATE Customerdto c SET c.status = 'Approved', c.bankmanager = ?2 WHERE c.id = ?1 AND c.status = 'Unapproved'")
	    void approveCustomerByIdByBankManager(Long customerId, BankManagerdto bankmanager, Long bankManagerId);

}
