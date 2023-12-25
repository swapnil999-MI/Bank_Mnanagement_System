package com.example.demo.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.dto.Admindto;
import com.example.demo.dto.BankManagerdto;


@Repository
public interface AdmindtoRepository extends JpaRepository<Admindto, Long> {
	    Admindto findBygmail(String gmail);
	    @Transactional
	    @Modifying
	    @Query("UPDATE BankManagerdto m SET m.status = 'Approved', m.admin = ?1 WHERE m.status = 'Unapproved'")
	    void approveAllUnapprovedManagersByAdminId(Admindto admin);

	    
	    //@Transactional
	    //@Modifying
	   // @Query("UPDATE BankManagerdto m SET m.status = 'Approved', m.admin_id = ?2 WHERE m.id = ?1 AND m.status = 'Unapproved'")
	    // void approveBankManagerByIdByAdminId(Long id, Long admin_id);

	    @Transactional
	    @Modifying
	    @Query("UPDATE BankManagerdto c SET c.status = 'Approved', c.admin = ?2 WHERE c.id = ?1 AND c.status = 'Unapproved'")
	    void approveBankManagerByIdByAdminId(Long id, Admindto admin);
}
