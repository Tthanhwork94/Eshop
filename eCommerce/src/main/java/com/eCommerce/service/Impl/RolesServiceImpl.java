package com.eCommerce.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eCommerce.entity.Roles;
import com.eCommerce.repository.RolesRepo;
import com.eCommerce.service.RolesService;

@Service
public class RolesServiceImpl implements RolesService{
	@Autowired
	private RolesRepo repo;

	@Override
	public Roles findByDescription(String description) {
		// TODO Auto-generated method stub
		return repo.findByDescription(description);
	}
	
}
