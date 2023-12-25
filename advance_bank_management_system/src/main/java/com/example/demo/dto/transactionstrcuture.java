package com.example.demo.dto;

public class transactionstrcuture {
	String sender_account_No;
    String sender_ifsc_code;
    double amount;
    String reciver_account_No;
    String reciver_ifsc_code;
    
	
    public String getSender_account_No() {
		return sender_account_No;
	}
	public void setSender_account_No(String sender_account_No) {
		this.sender_account_No = sender_account_No;
	}
	public String getSender_ifsc_code() {
		return sender_ifsc_code;
	}
	public void setSender_ifsc_code(String sender_ifsc_code) {
		this.sender_ifsc_code = sender_ifsc_code;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public String getReciver_account_No() {
		return reciver_account_No;
	}
	public void setReciver_account_No(String reciver_account_No) {
		this.reciver_account_No = reciver_account_No;
	}
	public String getReciver_ifsc_code() {
		return reciver_ifsc_code;
	}
	public void setReciver_ifsc_code(String reciver_ifsc_code) {
		this.reciver_ifsc_code = reciver_ifsc_code;
	}
	

}
