package com.eCommerce.service;

import java.sql.SQLException;
import java.util.List;

import com.eCommerce.entity.Users;

public interface UsersService {
	Users doLogin(String username,String password);
	Users save(Users user) throws SQLException;
	Integer countUsers();
	List<Users> findAll();
	Users findById(Long id);
	void deleteLogic(String username);
	Users doLoginAdmin(String username,String password);
}
