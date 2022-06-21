package com.eCommerce.service.Impl;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eCommerce.dto.CartDetailDto;
import com.eCommerce.dto.CartDto;
import com.eCommerce.entity.Orders;
import com.eCommerce.entity.Products;
import com.eCommerce.entity.Users;
import com.eCommerce.service.CheckoutService;
import com.eCommerce.service.OrderDetailsService;
import com.eCommerce.service.OrdersService;
import com.eCommerce.service.ProductsService;

@Service
public class CheckoutServiceImpl implements CheckoutService{

	
	@Autowired
	private ProductsService productsService;
	
	@Autowired
	private OrdersService ordersService;
	
	@Autowired
	private OrderDetailsService orderDetailsService;
	
	@Override
	@Transactional(rollbackOn = {Exception.class,Error.class})
	public void insert(CartDto cart, Users users, String address, String phone) throws Exception {
		//insert vao order
		Orders orders=new Orders();
		orders.setUsers(users);
		orders.setAddress(address);
		orders.setPhone(phone);
		//duyet hashmap de insert vao orderdetails va update product quantity
		
		try {
			Orders ordersRespone = ordersService.insert(orders);
			
			for(CartDetailDto cartDetail : cart.getDetails().values()) {
				cartDetail.setOrderId(ordersRespone.getId());
				orderDetailsService.insert(cartDetail);
				//update quantity
				Products product=productsService.findById(cartDetail.getProductId());
				Integer newQuantity=product.getQuantity()-cartDetail.getQuantity();
				productsService.updateQuantity(newQuantity, product.getId());
			}
		}catch(Exception e) {
			e.printStackTrace();
			throw new Exception("error quantity");
		}
	}


}
