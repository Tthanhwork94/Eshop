package com.eCommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.eCommerce.entity.Users;

@Repository
public interface UsersRepo extends JpaRepository<Users, Long> {
	Users findByUsername(String username);
	
	//saveAndFlush luu vao database
}
