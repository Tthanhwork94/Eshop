package com.mockproject.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.stereotype.Service;

import com.mockproject.entity.Users;

public interface UsersService {
	
	Users doLogin(String username, String password);
	Users save(Users user) throws SQLException;
	List<Users> findAll();
	void deleteLogical(String username);
	Users findByUsername(String username);
	public void update(Users user);
}
