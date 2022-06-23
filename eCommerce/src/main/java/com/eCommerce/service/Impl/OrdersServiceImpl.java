package com.eCommerce.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eCommerce.entity.Orders;
import com.eCommerce.repository.OrdersRepo;
import com.eCommerce.service.OrdersService;

@Service
public class OrdersServiceImpl implements OrdersService{

	@Autowired
	OrdersRepo repo;
	
	@Override
	public Orders insert(Orders orders) {
		// TODO Auto-generated method stub
		return repo.saveAndFlush(orders);
	}

	@Override
	public Integer countOrders() {
		// TODO Auto-generated method stub
		return repo.countOrders();
	}

}
