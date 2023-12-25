package com.example.demo.service;

import java.time.LocalDate;
import java.time.LocalTime;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.example.demo.dto.BankAccountdto;
import com.example.demo.dto.Customerdto;
import com.example.demo.dto.TransactionHistory;
import com.example.demo.dto.ResponseStructure;
import com.example.demo.dto.transactionstrcuture;
import com.example.demo.repository.BankAccountRepository;
import com.example.demo.repository.CustomerRepository;
import com.example.demo.repository.transactionhistoryRepository;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;


@Service
public class TreansactionService {
	BankAccountRepository bankaccountrepository;
	CustomerRepository customerrepository;
	transactionhistoryRepository transactionhistoryrepository;
	JavaMailSender emailSender;
	TransactionHistory trxhistory = new TransactionHistory();
	
	ResponseStructure<Customerdto> responsestructure = new ResponseStructure<Customerdto>();
	public TreansactionService(BankAccountRepository bankaccountrepository, JavaMailSender emailSender,CustomerRepository customerrepository,transactionhistoryRepository transactionhistoryrepository) {
		this.bankaccountrepository= bankaccountrepository;
		this.emailSender = emailSender;
		this.customerrepository= customerrepository;
		this.transactionhistoryrepository = transactionhistoryrepository;
	}
	
	public ResponseStructure<Customerdto> transferfund(transactionstrcuture transaction){
		BankAccountdto senderaccount = bankaccountrepository.findByAccountNo(transaction.getSender_account_No());
		BankAccountdto reciveraccount = bankaccountrepository.findByAccountNo(transaction.getReciver_account_No());
		if(senderaccount == null) {
			responsestructure.setMessage("Sender account number is wrong");
			responsestructure.setStatus(HttpStatus.CREATED.value());
			responsestructure.setData(null);
		}
		else if(reciveraccount == null){
			responsestructure.setMessage("Reciver account number is wrong");
			responsestructure.setStatus(HttpStatus.CREATED.value());
			responsestructure.setData(null);
		}
		else if(!senderaccount.getIfsc_code().equals(transaction.getSender_ifsc_code())) {
			responsestructure.setMessage("Sender IFSC Code is incorrect");
			responsestructure.setStatus(HttpStatus.CREATED.value());
			responsestructure.setData(null);
		}
		else if(!reciveraccount.getIfsc_code().equals(transaction.getReciver_ifsc_code())) {
			responsestructure.setMessage("Reciver IFSC Code is incorrect");
			responsestructure.setStatus(HttpStatus.CREATED.value());
			responsestructure.setData(null);
		}
		else if(senderaccount.getCustomer().getStatus().equals("Unapproved")) {
			responsestructure.setMessage("Sender account is not approved for any transaction please Contact to your responsible branch to get approved!");
			responsestructure.setStatus(HttpStatus.CREATED.value());
			responsestructure.setData(null);
		}
		else if(reciveraccount.getCustomer().getStatus().equals("Unapproved")){
			responsestructure.setMessage("Reciver account is not approved for any transacction!");
			responsestructure.setStatus(HttpStatus.CREATED.value());
			responsestructure.setData(null);
		}
		else {
			double senderblalance = senderaccount.getBalance();
			double transferamount= transaction.getAmount();
			if(senderblalance >= transferamount) {
			
			double amount = senderaccount.getBalance() - transaction.getAmount();
			senderaccount.setBalance(amount);
			bankaccountrepository.save(senderaccount);
			sendNotificationEmail(senderaccount,"A/C "+senderaccount.getAccount_no()+" Debited by Rs. "+transaction.getAmount()+" Total Balance: Rs. "+senderaccount.getBalance()+"CR Clr.Call 1800221911 if txn not done by you to block account/card.-RBOI ");
			double reciverupdatedbalance = transaction.getAmount()+reciveraccount.getBalance();
			reciveraccount.setBalance(reciverupdatedbalance);
			bankaccountrepository.save(reciveraccount);
			sendNotificationEmail(reciveraccount,"A/C "+reciveraccount.getAccount_no()+" Credited by Rs. "+transaction.getAmount()+" Total Balance: Rs. "+reciveraccount.getBalance()+"CR Clr. Never share OTP/Password for EMI postponement or any reason.-RBOI");
			trxhistory.setSenderAccountNo(senderaccount.getAccount_no());
			trxhistory.setReceiverAccountNo(reciveraccount.getAccount_no());
			trxhistory.setTransactionAmount(transaction.getAmount());
			trxhistory.setTransactionDate(LocalDate.now());
			trxhistory.setTransactionTime(LocalTime.now());
			transactionhistoryrepository.save(trxhistory);
			responsestructure.setMessage("Amount Sent Successfully");
			responsestructure.setStatus(HttpStatus.CREATED.value());
			responsestructure.setData(null);
			}
			else {
				responsestructure.setMessage("Sorry You don't have sufficent fund in your accoun to transfer");
				responsestructure.setStatus(HttpStatus.CREATED.value());
				responsestructure.setData(null);
			}
			
		}
		return responsestructure;
	}
	
	private void sendNotificationEmail(BankAccountdto account,String message1) {
		BankAccountdto account1 = account;
		Customerdto customer = account1.getCustomer();
		
        String senderEmail = "swapniljagadale007@gmail.com";
        String receiverEmail = customer.getEmail();

        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(senderEmail); // Set sender's email address
	    message.setTo(receiverEmail);
        message.setSubject("Transaction Alert");
        message.setText(message1);

        emailSender.send(message);
    }

}
