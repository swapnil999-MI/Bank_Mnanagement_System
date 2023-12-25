package com.example.demo.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.Customerdto;
import com.example.demo.dto.ResponseStructure;
import com.example.demo.dto.customersaveinput;
import com.example.demo.service.CustomerService;

@RestController
public class CustomerController {
	CustomerService customerservice;
	
	
	public CustomerController(CustomerService customerservice) {
		this.customerservice = customerservice;
	}
	
	 @PostMapping("/management/savecustomer")
	 ResponseStructure<Customerdto> savecustomer(@RequestBody customersaveinput customer){
		 return customerservice.savecustomer(customer);
	 }
	 @DeleteMapping("/management/deletecustomer")
	 ResponseStructure<Customerdto> deletecustomer(@RequestBody customersaveinput customer){
		 return customerservice.deletecustomer(customer);
	 }

}
