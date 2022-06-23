package com.eCommerce.service;

import com.eCommerce.entity.Orders;

public interface OrdersService {
	Orders insert(Orders orders);
	Integer countOrders();
}
