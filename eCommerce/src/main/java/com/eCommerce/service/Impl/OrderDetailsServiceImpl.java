package com.eCommerce.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eCommerce.dto.CartDetailDto;
import com.eCommerce.entity.Orders;
import com.eCommerce.repository.OrdersDetailRepo;
import com.eCommerce.service.OrderDetailsService;
import com.eCommerce.service.OrdersService;

@Service
public class OrderDetailsServiceImpl implements OrderDetailsService{

	@Autowired
	OrdersDetailRepo repo;

	@Override
	public void insert(CartDetailDto cartDetailDto) {
		// TODO Auto-generated method stub
		repo.insert(cartDetailDto);
	}

	@Override
	public Double sumSales() {
		// TODO Auto-generated method stub
		return repo.sumSales();
	}
	
	

}
