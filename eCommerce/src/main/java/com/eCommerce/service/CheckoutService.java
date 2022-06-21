package com.eCommerce.service;

import com.eCommerce.dto.CartDto;
import com.eCommerce.entity.Users;

public interface CheckoutService {
	void insert(CartDto cart,Users users,String address,String phone) throws Exception;
}
