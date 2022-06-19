package com.eCommerce.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.eCommerce.entity.Roles;

public interface RolesService {
	
	Roles findByDescription(String description);
}
