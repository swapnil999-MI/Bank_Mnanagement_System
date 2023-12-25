package com.example.demo.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.BankManagerdto;
import com.example.demo.dto.ResponseStructure;
import com.example.demo.dto.customerapprovestructure;
import com.example.demo.service.BankManagerService;

@RestController
public class BankManagerController {
	  public BankManagerService bankmanagerservice;
	  
	  public BankManagerController(BankManagerService bankmanagerservice) {
		  this.bankmanagerservice = bankmanagerservice;
	  }
	  
	  
	  @PostMapping("/management/savemanager")
		public ResponseStructure<BankManagerdto> saveUser(@RequestBody BankManagerdto manager) {
			return bankmanagerservice.savemanager(manager);
	  }
	  
	  
	  @DeleteMapping("/management/deletemanager/{email}")
	  public ResponseStructure<BankManagerdto> deletemanager(@PathVariable String email){
		  return bankmanagerservice.deletemanager(email);
	  }
	  
	  @PostMapping("/management/approvecustomer")
	  public ResponseStructure<BankManagerdto> approvecustomer(@RequestBody customerapprovestructure customertobeapproved){
		return bankmanagerservice.approvecustomer(customertobeapproved);
		  
	  }

	  } 
