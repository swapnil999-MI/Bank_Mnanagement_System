package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.dto.Branchifsccodedto;


@Repository
public interface BranchifsccodeRepository extends JpaRepository<Branchifsccodedto, Long> {
       Branchifsccodedto findBybranch(String branch);
}
