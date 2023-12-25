package com.example.demo.service;

import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.example.demo.dto.BankManagerdto;
import com.example.demo.dto.Customerdto;
import com.example.demo.dto.ResponseStructure;
import com.example.demo.dto.customerapprovestructure;
import com.example.demo.repository.BankManagerRepository;
import com.example.demo.repository.CustomerRepository;



@Service
public class BankManagerService {
	BankManagerRepository bankmanagerrepository;
	CustomerRepository customerrepository;
	
	public BankManagerService(BankManagerRepository bankmanagerrepository,CustomerRepository customerrepository) {
		this.bankmanagerrepository = bankmanagerrepository;
		this.customerrepository = customerrepository;
	}
	ResponseStructure<BankManagerdto> responsestructure = new ResponseStructure<BankManagerdto>();
	
	public ResponseStructure<BankManagerdto> savemanager(BankManagerdto manager){
		BankManagerdto existmanager = bankmanagerrepository.findBygmail(manager.getGmail());
		if(existmanager == null) {
			bankmanagerrepository.saveBankManager(manager);
			responsestructure.setMessage("Manager is saved succesfully");
			responsestructure.setStatus(HttpStatus.CREATED.value());
			responsestructure.setData(null);
		}
		else {
			responsestructure.setMessage("Manager is all ready exist and working");
			responsestructure.setStatus(HttpStatus.CREATED.value());
			responsestructure.setData(null);
		}
		
		return responsestructure;
	}
	
	
	public ResponseStructure<BankManagerdto> deletemanager(String email){
		BankManagerdto manager = bankmanagerrepository.findBygmail(email);
		
		if(manager != null) {
			bankmanagerrepository.delete(manager);
			responsestructure.setMessage("Manager is deleted successfully");
			responsestructure.setStatus(HttpStatus.CREATED.value());
			responsestructure.setData(null);
		}
		else {
			responsestructure.setMessage("Manager is not exist");
			responsestructure.setStatus(HttpStatus.CREATED.value());
			responsestructure.setData(null);
		}
		
		return responsestructure;
		
	}
	
	
	public ResponseStructure<BankManagerdto> approvecustomer(customerapprovestructure customertobeapproved){
		BankManagerdto responsiblemanager = bankmanagerrepository.findBygmail(customertobeapproved.getManageremail());
		if(responsiblemanager != null) {
			if(responsiblemanager.getPassword().equals(customertobeapproved.getManagerpassword())) {
				if(customertobeapproved.getCustomeremail().equals("approveallcustomers")) {
					bankmanagerrepository.approveAllUnapprovedCustomersByBankManager( responsiblemanager,responsiblemanager.getId());
					responsestructure.setMessage("All Customers are approved successfully");
					responsestructure.setStatus(HttpStatus.CREATED.value());
					responsestructure.setData(null);
				}
				else {
					
					Customerdto cutomerapprove = customerrepository.findByemail(customertobeapproved.getCustomeremail());
					if (cutomerapprove != null) {
					bankmanagerrepository.approveCustomerByIdByBankManager(cutomerapprove.getId(), responsiblemanager, responsiblemanager.getId());
					responsestructure.setMessage("Customer '"+cutomerapprove.getEmail() +"' is approved successfully");
					responsestructure.setStatus(HttpStatus.CREATED.value());
					responsestructure.setData(null);
					}
					else {
						responsestructure.setMessage("Customer with '"+ customertobeapproved.getCustomeremail() +"' is not exist!");
						responsestructure.setStatus(HttpStatus.CREATED.value());
						responsestructure.setData(null);
					}
				}
			}
			else {
				responsestructure.setMessage("Manager Password is wrong!");
				responsestructure.setStatus(HttpStatus.CREATED.value());
				responsestructure.setData(null);
			}
			
		}
		else {
			responsestructure.setMessage("Manager with '"+customertobeapproved.getManageremail()+"'  is not exist!");
			responsestructure.setStatus(HttpStatus.CREATED.value());
			responsestructure.setData(null);
		}
		return responsestructure;
		
	}
	

}
