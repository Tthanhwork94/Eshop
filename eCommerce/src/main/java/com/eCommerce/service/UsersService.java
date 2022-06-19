package com.eCommerce.service;

import java.sql.SQLException;

import com.eCommerce.entity.Users;

public interface UsersService {
	Users doLogin(String username,String password);
	Users save(Users user) throws SQLException;
}
