package com.example.demo.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.Customerdto;
import com.example.demo.dto.ResponseStructure;
import com.example.demo.dto.transactionstrcuture;
import com.example.demo.service.TreansactionService;

@RestController
public class Transactioncontroller {
	TreansactionService transactionservice;
	
	public Transactioncontroller(TreansactionService transactionservice) {
		this.transactionservice = transactionservice;
	}
	
	@PostMapping("/transferfund")
	ResponseStructure<Customerdto> transferfund(@RequestBody transactionstrcuture transaction ){
		return transactionservice.transferfund(transaction);
		
	}
	

}
