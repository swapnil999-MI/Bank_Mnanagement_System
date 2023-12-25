package com.example.demo.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.dto.BankManagerdto;
import com.example.demo.dto.Customerdto;

@Repository
public interface CustomerRepository extends JpaRepository<Customerdto, Long> {
	Customerdto findByemail(String email);
	default void saveCustomer(Customerdto customer) {
		  customer.setStatus("Unapproved");
		  save(customer);
	}
    // Define custom query methods if needed
}
