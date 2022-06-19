package com.mockproject.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.mockproject.dto.CartDetailDto;
import com.mockproject.repository.OrderDetailsRepo;

public interface OrderDetailsService {
	
	void insert(CartDetailDto cartDetailDto);
}
