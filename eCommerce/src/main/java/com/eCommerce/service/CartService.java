package com.eCommerce.service;

import com.eCommerce.dto.CartDto;

public interface CartService {
		
	CartDto updateCart(CartDto cart,Long productId,Integer quantity,Boolean isReplace);
	Integer getTotalQuantity(CartDto cart);
	Double getTotalPrice(CartDto cart);
}
