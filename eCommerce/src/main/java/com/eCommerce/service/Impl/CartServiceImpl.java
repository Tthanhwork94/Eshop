package com.eCommerce.service.Impl;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eCommerce.dto.CartDetailDto;
import com.eCommerce.dto.CartDto;
import com.eCommerce.entity.Products;
import com.eCommerce.service.CartService;
import com.eCommerce.service.ProductsService;

@Service
public class CartServiceImpl implements CartService {

	@Autowired
	private ProductsService productsService;
	
	@Override
	public CartDto updateCart(CartDto cart, Long productId, Integer quantity, Boolean isReplace) {
		
		//them moi
		// thay doi
		// xoa
		Products product = productsService.findById(productId);
		HashMap<Long,CartDetailDto> details=cart.getDetails();

		if(!details.containsKey(productId)) {
			//insert
			CartDetailDto newDetail=createNewCartDetail(product, quantity);
			details.put(productId, newDetail);
		}else if (quantity>0){
			//update
			if(isReplace) {
				//thay the
				details.get(productId).setQuantity(quantity);
				
			}else {
				//cong don
				Integer newQuantity=details.get(productId).getQuantity()+quantity;
				details.get(productId).setQuantity(newQuantity);
			}
		}else {
			//delete
			details.remove(product.getId());
		}
		cart.setTotalQuantity(getTotalQuantity(cart));
		cart.setTotalPrice(getTotalPrice(cart));
		
		return cart;
	}
	

	@Override
	public Integer getTotalQuantity(CartDto cart) {
		Integer totalQuantity=0;
		HashMap<Long,CartDetailDto> details = cart.getDetails();
		for(CartDetailDto detail: details.values()) {
			totalQuantity+=detail.getQuantity();
		}
		return totalQuantity;
	}

	@Override
	public Double getTotalPrice(CartDto cart) {
		Double totalPrice =0D;
		HashMap<Long,CartDetailDto> details = cart.getDetails();
		for(CartDetailDto detail: details.values()) {
			totalPrice+=detail.getPrice()*detail.getQuantity();
		}
		return totalPrice;
	}
	
	private CartDetailDto createNewCartDetail(Products product,Integer quantity) {
		CartDetailDto newDetail=new CartDetailDto();
		newDetail.setProductId(product.getId());
		newDetail.setQuantity(quantity);
		newDetail.setName(product.getProductName());
		newDetail.setImgUrl(product.getImgUrl());
		newDetail.setPrice(product.getPrice());
		newDetail.setSlug(product.getSlug());
		return newDetail;
	}
}
