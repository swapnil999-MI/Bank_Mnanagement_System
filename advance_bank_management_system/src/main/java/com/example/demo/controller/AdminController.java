package com.example.demo.controller;


import com.example.demo.service.AdminService;
import org.springframework.web.bind.annotation.*;

import com.example.demo.dto.Admindto;
import com.example.demo.dto.Branchifsccodedto;
import com.example.demo.dto.ResponseStructure;
import com.example.demo.dto.approveStructure;


@RestController
public class AdminController {
     private final AdminService adminService;
	
	public AdminController(AdminService adminService){
		this.adminService=adminService;
	}
	
	@PostMapping("/management/saveadmin")
	public ResponseStructure<Admindto> saveAdmin() {
		Admindto admin = new Admindto();
	
				admin.setName("SwapnilAdmin");
				
	
				admin.setGmail("swapniljagadale999@gmail.com");

				admin.setCno("8928684454");
	      		return adminService.saveAdmin(admin);
	}

	@DeleteMapping("/management/deleteadmin/{email}")
	public ResponseStructure<Admindto> deleteAdmin(@PathVariable String email )  {
		return adminService.deleteAdmin(email);
	}
	
	
	@PostMapping("/management/approvemanager")
	public ResponseStructure<Admindto> approvemanager(@RequestBody approveStructure email){
		return adminService.approvemanager(email);
	}
	
	@PostMapping("/management/addbranch")
	public ResponseStructure<Admindto> addbranch(@RequestBody Branchifsccodedto branch){
		return adminService.addbranch(branch);
	}
	
	

}
