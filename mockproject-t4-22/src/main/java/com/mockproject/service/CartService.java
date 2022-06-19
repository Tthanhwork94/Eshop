package com.mockproject.service;

import javax.servlet.http.HttpSession;

import com.mockproject.dto.CartDto;
import com.mockproject.entity.Users;

public interface CartService {
	CartDto update(CartDto cart,Long productId,Integer quantity,boolean isReplace);
	Integer getTotalQuantity(CartDto cart);
	Double getTotalPrice(CartDto cart);
	void Insert(CartDto cart, Users user,String address, String phone) throws Exception;
}
