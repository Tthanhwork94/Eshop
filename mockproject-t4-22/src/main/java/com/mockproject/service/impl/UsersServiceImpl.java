package com.mockproject.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.mockproject.entity.Roles;
import com.mockproject.entity.Users;
import com.mockproject.repository.UsersRepo;
import com.mockproject.service.RolesService;
import com.mockproject.service.UsersService;

@Service
public class UsersServiceImpl implements UsersService {
	
	private BCryptPasswordEncoder bcrypt=new BCryptPasswordEncoder();
	
	@Autowired
	private UsersRepo repo;
	
	@Autowired
	private RolesService rolesService;

	@Override
	public Users doLogin(String username, String password) {
		
		Users user = repo.findByUsername(username);
		
		if(user!=null) {
			Boolean CheckHashPassword=bcrypt.matches(password, user.getHashPassword());
			return CheckHashPassword ? user : null;	
		}else {
			return null;
		}
	}

	@Transactional(rollbackOn = {Exception.class,Throwable.class})
	@Override
	public Users save(Users user) {
		Roles role = rolesService.findByDescription("user");
		user.setRoles(role);
		user.setIsDeleted(Boolean.FALSE);
		String hashPassword=bcrypt.encode(user.getHashPassword());
		user.setHashPassword(hashPassword);
		return repo.saveAndFlush(user);
	}

	

	@Override
	public List<Users> findAll() {
		// TODO Auto-generated method stub
		return repo.findByIsDeleted(false);
	}

	@Override
	@Transactional(rollbackOn = {Exception.class,Throwable.class})
	public void deleteLogical(String username) {
		repo.deleteLogical(username);
	}

	@Override
	public Users findByUsername(String username) {
		 return repo.findByUsername(username);
	}

	@Override
	@Transactional(rollbackOn = {Exception.class,Throwable.class})
	public void update(Users user) {
		if(org.apache.commons.lang3.ObjectUtils.isNotEmpty(user) && StringUtils.isEmpty(user.getHashPassword())) {
			repo.updateNonPassword(user.getEmail(), user.getFullname(),user.getUsername());
		}else {
			String hashPassword=bcrypt.encode(user.getHashPassword());
			user.setHashPassword(hashPassword);
			repo.updateHasPassword(user.getEmail(), hashPassword, user.getFullname(),user.getUsername());
		}
			
	}

	
	

}
