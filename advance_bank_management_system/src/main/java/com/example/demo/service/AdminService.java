package com.example.demo.service;

import java.util.*;
import org.springframework.stereotype.Service;
import com.example.demo.repository.AdmindtoRepository;
import com.example.demo.repository.BankManagerRepository;
import com.example.demo.repository.BranchifsccodeRepository;
import com.example.demo.dto.Admindto;
import com.example.demo.dto.BankManagerdto;
import com.example.demo.dto.Branchifsccodedto;
import com.example.demo.dto.ResponseStructure;
import com.example.demo.dto.approveStructure;
import com.example.demo.service.BankManagerService;
import org.springframework.http.HttpStatus;



@Service
public class AdminService {
	BankManagerRepository bankmanagerrepository;
	BranchifsccodeRepository branchifsccoderepository;
	private final AdmindtoRepository adminrepository ;
	
	public AdminService(AdmindtoRepository adminrepository,BankManagerRepository bankmanagerrepository,BranchifsccodeRepository branchifsccoderepository) {
		this.adminrepository = adminrepository;
		this.bankmanagerrepository = bankmanagerrepository;
		this.branchifsccoderepository = branchifsccoderepository;
	}
	ResponseStructure<Admindto> responsestructure = new ResponseStructure<Admindto>();
	public ResponseStructure<Admindto> saveAdmin(Admindto admin){
		Admindto existingadmin = adminrepository.findBygmail(admin.getGmail());
		if(existingadmin == null && admin.getGmail() != null) {
		adminrepository.save(admin);
		responsestructure.setMessage("admin is saved succesfully");
		responsestructure.setStatus(HttpStatus.CREATED.value());
		responsestructure.setData(null);
		}
		else {
			responsestructure.setMessage("admin is already saved");
			responsestructure.setStatus(HttpStatus.CREATED.value());
			responsestructure.setData(null);
		}
		return responsestructure;
		
	}
	
	public ResponseStructure<Admindto> deleteAdmin(String email){
		Admindto getadmindto = adminrepository.findBygmail(email);
		if(getadmindto == null) {
			responsestructure.setMessage("sorry this admin is not exist");
			responsestructure.setStatus(HttpStatus.CREATED.value());
			responsestructure.setData(null);
		}
		else {
			adminrepository.delete(getadmindto);
			responsestructure.setMessage("admin is deleted successfully");
			responsestructure.setStatus(HttpStatus.CREATED.value());
			responsestructure.setData(null);
		}
		 return responsestructure;
	}
	
	public ResponseStructure<Admindto> approvemanager(approveStructure email){
		Admindto responsibleadmin = adminrepository.findBygmail(email.getadminemail());
		if (email.getmanageremail().equals("approveallmanager")){
			adminrepository.approveAllUnapprovedManagersByAdminId(responsibleadmin);
			responsestructure.setMessage("All Managers are approved successfully");
			responsestructure.setStatus(HttpStatus.CREATED.value());
			responsestructure.setData(null);
			
		} 
		else {
			BankManagerdto manager = bankmanagerrepository.findBygmail(email.getmanageremail());
			if (manager != null) {
				adminrepository.approveBankManagerByIdByAdminId(manager.getId(), responsibleadmin);
				responsestructure.setMessage("Manager is approved successfully successfully");
				responsestructure.setStatus(HttpStatus.CREATED.value());
				responsestructure.setData(null);
			}
			else {
				responsestructure.setMessage("Manager with this email is not exist");
				responsestructure.setStatus(HttpStatus.CREATED.value());
				responsestructure.setData(null);
				
			}
			
		}
		return responsestructure;
	}
	
	public ResponseStructure<Admindto> addbranch(Branchifsccodedto branch){
		Branchifsccodedto branch1 = branchifsccoderepository.findBybranch(branch.getBranch());
		if(branch1 == null) {
		branchifsccoderepository.save(branch);
		responsestructure.setMessage("Branch "+branch.getBranch()+" is saved successfully with IFSC Code "+branch.getIfsc_code());
		responsestructure.setStatus(HttpStatus.CREATED.value());
		responsestructure.setData(null);
		}
		else {
			responsestructure.setMessage("Branch "+branch.getBranch()+" is all ready present");
			responsestructure.setStatus(HttpStatus.CREATED.value());
			responsestructure.setData(null);
		}
		
		return responsestructure;
	}

}
