package com.mockproject.service;

import com.mockproject.entity.Orders;

public interface OrdersService {
	Orders insert(Orders order);
}

//saveAndFlush -> nhan vao tham so entity
//dung cho ca INSERT va UPDATE