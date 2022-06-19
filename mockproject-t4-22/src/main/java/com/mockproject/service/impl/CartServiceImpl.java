package com.mockproject.service.impl;

import java.util.HashMap;

import javax.transaction.Transactional;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;

import com.mockproject.dto.CartDetailDto;
import com.mockproject.dto.CartDto;
import com.mockproject.entity.Orders;
import com.mockproject.entity.Products;
import com.mockproject.entity.Users;
import com.mockproject.service.CartService;
import com.mockproject.service.OrderDetailsService;
import com.mockproject.service.OrdersService;
import com.mockproject.service.ProductsService;

@Service
public class CartServiceImpl implements CartService{

	
	@Autowired
	private ProductsService productsService;
	
	@Autowired
	private OrdersService ordersService;
	
	@Autowired
	private OrderDetailsService orderDetailsService;
	
	@Override
	public CartDto update(CartDto cart, Long productId, Integer quantity, boolean isReplace){
		// TODO Auto-generated method stub
		
		//Lay san pham ra
		Products product = productsService.findById(productId);
		
		//Lay ra list Details trong CartDto
		HashMap<Long, CartDetailDto> details=cart.getDetails();
		
		//1. them moi san pham
		//2. update san pham
		//		-cong don
		//		-replace
		//3. delete
		if(!details.containsKey(productId)) {
			//them moi
			CartDetailDto newDetail=createNewCartDetail(product, quantity);
			details.put(productId, newDetail);
		}else if(quantity>0) {
			
			if(isReplace) {
				//replace
				details.get(productId).setQuantity(quantity);
				
			}else {
				//cong don
				Integer oldQuantity=details.get(productId).getQuantity();
				details.get(productId).setQuantity(quantity+oldQuantity);
			}
	
		}else {
			//delete
			details.remove(productId);
		}
		
		//update totalquantity
		cart.setTotalQuantity(getTotalQuantity(cart));
		//update totalprice
		cart.setTotalPrice(getTotalPrice(cart));
		return cart;
	}
	
	

	@Override
	public Integer getTotalQuantity(CartDto cart) {
		Integer totalQuantity =0;
		HashMap<Long,CartDetailDto> details = cart.getDetails();
		for (CartDetailDto cartDetail : details.values()) {
			totalQuantity += cartDetail.getQuantity();
		}
		return totalQuantity;
	}

	@Override
	public Double getTotalPrice(CartDto cart) {
		Double totalPrice=0D;
		HashMap<Long,CartDetailDto> details=cart.getDetails();
		for (CartDetailDto cartDetail:details.values()) {
			totalPrice+=cartDetail.getQuantity()*cartDetail.getPrice();
		}
		return totalPrice;
	}
	
	@Transactional(rollbackOn = {Exception.class, Throwable.class})
	@Override
	public void Insert(CartDto cart, Users user, String address, String phone) throws Exception{
		
		if(StringUtils.isAnyBlank(address,phone)) {
			throw new Exception("Address or phone must be not null or empty or whitespace only");
		}
		
		//insert vao order
		Orders order = new Orders();
		order.setUsers(user);
		order.setAddress(address);
		order.setPhone(phone);
		
		try {
			Orders orderResponse = ordersService.insert(order);
			for(CartDetailDto cartDetailDto : cart.getDetails().values()) {
				Products product=productsService.findById(cartDetailDto.getProductId());
				if(product.getQuantity()>=cartDetailDto.getQuantity()) {
					
					cartDetailDto.setOrderId(orderResponse.getId());
					orderDetailsService.insert(cartDetailDto);
					
					Integer newQuantity=product.getQuantity()-cartDetailDto.getQuantity();
					productsService.updateQuantity(cartDetailDto.getProductId(), newQuantity);
				
				}else {
					throw new Exception("Order quantity must be less than current product quantity");
				}
			}
		}catch(Exception e) {
			e.printStackTrace();
			throw new Exception("Insert fail");
		}
		//duyet hashmap insert vao order_detail
		//update sl tung sp
		
		
	}

	private CartDetailDto createNewCartDetail(Products product,Integer quantity) {
		CartDetailDto detail=new CartDetailDto();
		
		detail.setProductId(product.getId());
		detail.setName(product.getName());
		detail.setPrice(product.getPrice());
		detail.setQuantity(quantity);
		detail.setImgUrl(product.getImgUrl());
		detail.setSlug(product.getSlug());
		
		return detail;
	}



	
}
