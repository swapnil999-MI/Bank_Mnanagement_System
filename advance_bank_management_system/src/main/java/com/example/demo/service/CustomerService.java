package com.example.demo.service;

import org.springframework.http.HttpStatus;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import java.util.Random;

import com.example.demo.dto.Customerdto;
import com.example.demo.dto.ResponseStructure;
import com.example.demo.dto.customersaveinput;
import com.example.demo.repository.BankAccountRepository;
import com.example.demo.repository.BankManagerRepository;
import com.example.demo.repository.CustomerRepository;
import com.example.demo.repository.BranchifsccodeRepository;
import com.example.demo.dto.BankAccountdto;
import com.example.demo.dto.BankManagerdto;
import com.example.demo.dto.Branchifsccodedto;
import com.example.demo.service.TreansactionService;

@Service
public class CustomerService {
	

	CustomerRepository customerrepository;
	BankManagerRepository bankmanagerrepository;
	BankAccountRepository bankaccountrepository;
	BranchifsccodeRepository branchrepository;
	JavaMailSender emailSender;

	Customerdto customertobesaved = new Customerdto();
	BankAccountdto bankaccount = new BankAccountdto();
	public CustomerService(CustomerRepository customerrepository,BankManagerRepository bankmanagerrepository,BankAccountRepository bankaccountrepository,BranchifsccodeRepository branchrepository,JavaMailSender emailSender) {
		this.customerrepository = customerrepository;
		this.bankmanagerrepository = bankmanagerrepository;
		this.bankaccountrepository = bankaccountrepository;
		this.branchrepository = branchrepository;
		this.emailSender = emailSender;

	}
	ResponseStructure<Customerdto> responsestructure = new ResponseStructure<Customerdto>();
	
	
	
	public String generateaccountno() {
		Random random = new Random();
		String accountno=null;;
		boolean isUnique = false;
		while(!isUnique) {
			accountno = String.format("%010d", Math.abs(random.nextLong() % 9000000000L + 1000000000L));
			BankAccountdto accountispresent = bankaccountrepository.findByAccountNo(accountno);
			if (accountispresent == null) {
				isUnique = true;
			}
		}
		return accountno;
	}
	public ResponseStructure<Customerdto> savecustomer(customersaveinput customer){
		
		BankManagerdto responsiblemanager = bankmanagerrepository.findBygmail(customer.getManageremail());
		if(responsiblemanager != null) {
			Branchifsccodedto responsiblebranch = branchrepository.findBybranch(customer.getBranch());
			 
			if(responsiblebranch != null) {
				if( responsiblemanager.getStatus().equals("Approved")) {
					if(responsiblemanager.getPassword().equals(customer.getManagerpassword())) {
						customertobesaved.setUsername(customer.getUsername());
						customertobesaved.setFirstName(customer.getFirstName());
						customertobesaved.setLastName(customer.getLastName());
						customertobesaved.setPhoneNumber(customer.getPhoneNumber());
						customertobesaved.setEmail(customer.getEmail());
						customertobesaved.setAddress(customer.getAddress());
						customertobesaved.setAddharCardNo(customer.getAadharCardNo());
						customerrepository.saveCustomer(customertobesaved);
						bankaccount.setCustomer(customertobesaved);
						bankaccount.setAccount_no(generateaccountno());
						bankaccount.setIfsc_code(responsiblebranch.getIfsc_code());
						bankaccount.setBalance(customer.getBalance());
						
					    bankaccountrepository.save(bankaccount);
						sendNotificationEmail(customer,"Congratulations! Your Bank Acount is Successfully created in RBI and Your account Number is =   "+bankaccount.getAccount_no()+" and Your IFSC Code is =   "+bankaccount.getIfsc_code());
						responsestructure.setMessage("Account is created successfully");
						responsestructure.setStatus(HttpStatus.CREATED.value());
						responsestructure.setData(null);
					}
					else {
						responsestructure.setMessage("Manager password is wrong!");
						responsestructure.setStatus(HttpStatus.CREATED.value());
						responsestructure.setData(null);
					}
				}
				else {
					responsestructure.setMessage("Manager '"+customer.getManageremail() +"' is not approved to work kindly contact to your responsible admin to be get approved");
					responsestructure.setStatus(HttpStatus.CREATED.value());
					responsestructure.setData(null);
				}
				
			}
			else {
				responsestructure.setMessage("Wrong Branch Name!");
				responsestructure.setStatus(HttpStatus.CREATED.value());
				responsestructure.setData(null);
			}
			
			
		}
		else {
			responsestructure.setMessage("Manager with email "+customer.getManageremail()+" is not exist!");
			responsestructure.setStatus(HttpStatus.CREATED.value());
			responsestructure.setData(null);
		}
		
		return responsestructure;
	}
	
	public ResponseStructure<Customerdto> deletecustomer(customersaveinput customer1){
		 bankaccountrepository.deleteAll();
		 customerrepository.deleteAll();
		 responsestructure.setMessage(customer1.getAadharCardNo()+customer1.getUsername());
			responsestructure.setStatus(HttpStatus.CREATED.value());
			responsestructure.setData(null);
		return responsestructure;
	}
	
	private void sendNotificationEmail(customersaveinput customer,String message1) {

        String senderEmail = "swapniljagadale007@gmail.com";
        String receiverEmail = customer.getEmail();

        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(senderEmail); // Set sender's email address
	    message.setTo(receiverEmail);
        message.setSubject("New Bank Account is Created in RBI bank");
        message.setText(message1);

        emailSender.send(message);
    }

	
		
}
