package com.example.demo.controller;

import java.util.*;

import com.example.demo.service.UserService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;
import com.example.demo.dto.User;
import com.example.demo.dto.ResponseStructure;

@RestController
public class controller {
	private final UserService userService;
	
	public controller(UserService userService){
		this.userService=userService;
	}
	
	@PostMapping("/save")
	public ResponseStructure<User> saveUser(@RequestBody User user) {
		return userService.saveuser(user);
	}
	@DeleteMapping("/delete/{username}")
	public Map<String, Object> delete(@PathVariable String username) {
		return userService.delete(username);
	}
	@GetMapping("/get/{username}")
	public ResponseStructure<User> getuser(@PathVariable String username){
		return userService.getuser(username);
	}
	@GetMapping("/getall")
	public ResponseStructure<List<User>> getalluser(){
		return userService.getuserall();
	}

}
