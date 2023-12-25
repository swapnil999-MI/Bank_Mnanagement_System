package com.example.demo.dto;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;


@Entity
@Table(name = "BankManagers")
public class BankManagerdto {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	private String gmail;
	private String cno;
	private String password;
	private String status;
	
	@ManyToOne
	@JoinColumn(name = "admin_id")
	private Admindto admin;
	@OneToMany(mappedBy = "bankmanager")
	private List<Customerdto> customer;
	
	public List<Customerdto> getCustomer() {
		return customer;
	}

	public void setCustomer(List<Customerdto> customer) {
		this.customer = customer;
	}
	
	public Admindto getAdmin() {
		return admin;
	}

	public void setAdmin(Admindto admin) {
		this.admin = admin;
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

	public void setCno(String cno) {
		this.cno = cno;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	//public long getAdmin_id() {
	//	return admin_id;
	//}

	//public void setAdmin_id(long admin_id) {
	//	this.admin_id = admin_id;
	//}

}
