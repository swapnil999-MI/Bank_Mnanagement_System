package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;



@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })

//@SpringBootApplication
public class Springcrud9Application {

	public static void main(String[] args) {
		SpringApplication.run(Springcrud9Application.class, args);
	}
}
