package com.example.demo.service;

import java.util.*;
import org.springframework.stereotype.Service;
import com.example.demo.repository.UserRepository;
import com.example.demo.dto.User;
import com.example.demo.dto.ResponseStructure;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Service
public class UserService {
	private final UserRepository userRepository;
	public UserService(UserRepository userRepository) {
		this.userRepository=userRepository;	
	}
    
	
	public 	ResponseStructure<User> saveuser(User username) {
		User usercheck = userRepository.findByUsernameOrPhoneNumberOrEmail(username.getUsername(), username.getPhoneNumber(), username.getEmail());
		ResponseStructure<User> responseStructure=new ResponseStructure<User>();
		if(usercheck == null) {
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder(15);
	    username.setPassword(passwordEncoder.encode(username.getPassword()));
		userRepository.save(username);
		responseStructure.setStatus(HttpStatus.CREATED.value());
		responseStructure.setMessage("User saved succesfully");
		return responseStructure;
		}
		else {
			responseStructure.setMessage("Sorry but User with this username or phone No. or Email is all ready present at us");
			return responseStructure;
		}
	}
	
	public Map<String, Object> delete(String username) {
		Map<String, Object> response = new HashMap<>();
		try {
			long userid =Integer.parseInt(username);
			Optional<User> opt1 = userRepository.findById(userid);
			
			if(opt1.isPresent()) {
				userRepository.delete(opt1.get());
				response.put("status",HttpStatus.CREATED.value());
				response.put("message", "user is deleted succesfully");

				return response;
			}
			else {
				response.put("status",HttpStatus.CREATED.value());
				response.put("message", "sorry but user with this username is not present");
				return response;	
		}
		}
		catch (NumberFormatException e){
			Optional<User> opt = userRepository.findByUsername(username);
			if(opt.isPresent()){
				userRepository.delete(opt.get());
				response.put("status",HttpStatus.CREATED.value());
				response.put("message", "user is deleted succesfully");
				return response;
			}
			else {
				response.put("status",HttpStatus.CREATED.value());
				response.put("message", "sorry but user with this username is not present");
				return response;
				
				}			
		}					
	}
	
	
	
	public ResponseStructure<User> getuser(String username){
		ResponseStructure<User> response = new ResponseStructure<User>();
	                  Optional<User> opt = userRepository.findByUsername(username);
		     if(opt.isPresent()) {
			    response.setStatus(HttpStatus.CREATED.value());
			    response.setMessage("hey i have procced your request and here is what i have for you");
			    response.setData(opt.get());
			    return response;
		                         }
	       	else {
		       response.setStatus(HttpStatus.NOT_FOUND.value());
		       response.setMessage("sorry bro but the username which you have given is not present at me");
		       return response;
		     }
	         
	}
	public ResponseStructure<List<User>> getuserall(){
		ResponseStructure<List<User>> response1 = new ResponseStructure<List<User>>();
		List<User> opt = userRepository.findAll();
		response1.setStatus(HttpStatus.CREATED.value());
	    response1.setMessage("hey i have procced your request and here is what i have for you");
	    response1.setData(opt);
	    return response1;
	}
}