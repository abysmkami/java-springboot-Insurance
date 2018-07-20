package com.Insurance.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Insurance.Model.InsuranceUser;


public interface InsuranceUserRepository extends JpaRepository<InsuranceUser, Long> {
	InsuranceUser findByUsername(String username);
	}
