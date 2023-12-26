package com.example.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.dto.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	Optional<User> findByUsername(String username);
	User findByUsernameOrPhoneNumberOrEmail(String username, String phoneNumber, String email);
    // Define additional methods if needed
}