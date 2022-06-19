package com.eCommerce.service.Impl;

import java.sql.SQLException;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.eCommerce.entity.Roles;
import com.eCommerce.entity.Users;
import com.eCommerce.repository.UsersRepo;
import com.eCommerce.service.RolesService;
import com.eCommerce.service.UsersService;

@Service
public class UsersServiceImpl implements UsersService{

	private BCryptPasswordEncoder bcrypt=new BCryptPasswordEncoder();
	
	@Autowired
	private UsersRepo repo;
	
	@Autowired
	private RolesService rolesService;

	@Override
	public Users doLogin(String username, String password) {
		Users user=repo.findByUsername(username);
		
		if(user!=null) {
			String hashPassword=user.getHashPassword();
			boolean checkPassword=bcrypt.matches(password, hashPassword);
			return checkPassword ? user : null;
		}else {
			return null;
		}	
	}

	@Override
	@Transactional(rollbackOn = {Exception.class, Throwable.class})
	public Users save(Users user) throws SQLException{
		Roles roleUser=rolesService.findByDescription("user");
		user.setRole(roleUser);
		user.setIsDeleted(Boolean.FALSE);
		String hashPassword=bcrypt.encode(user.getHashPassword());
		user.setHashPassword(hashPassword);
		return repo.saveAndFlush(user);
	}
	

	
}
