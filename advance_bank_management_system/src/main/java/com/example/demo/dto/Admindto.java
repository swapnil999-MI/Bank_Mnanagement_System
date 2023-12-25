package com.example.demo.dto;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "Admin") 

public class Admindto {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	private String gmail;
	private String cno;
	
	@OneToMany(mappedBy = "admin")
	private List<BankManagerdto> bankManager;
	
	public List<BankManagerdto> getBankManager() {
		return bankManager;
	}

	public void setBankManager(List<BankManagerdto> bankManager) {
		this.bankManager = bankManager;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGmail() {
		return gmail;
	}

	public void setGmail(String gmail) {
		this.gmail = gmail;
	}

	public String getCno() {
		return cno;
	}

	public void setCno(String i) {
		this.cno = i;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}




}
