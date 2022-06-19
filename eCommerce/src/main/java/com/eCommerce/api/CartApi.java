package com.eCommerce.api;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.eCommerce.dto.CartDto;
import com.eCommerce.service.CartService;
import com.eCommerce.util.SessionUtil;

@RestController
@RequestMapping("/api/cart")
public class CartApi {
	
	// /api/cart/update?productId=...&quantity=...&isReplace=...
	
	@Autowired
	private CartService cartService;
	
	@GetMapping("/update")
	public ResponseEntity<?> doGetUpdate(@RequestParam("productId") Long productId,
			@RequestParam("quantity") Integer quantity,
			@RequestParam("isReplace") Boolean isReplace,
			HttpSession session){
		CartDto currentCart=SessionUtil.getCurrentCart(session);
		cartService.updateCart(currentCart, productId, quantity, isReplace);
		return ResponseEntity.ok(currentCart);
	}
	
	@GetMapping("/refresh")
	public ResponseEntity<?> doGetRefresh(HttpSession session){
		CartDto currentCart=SessionUtil.getCurrentCart(session);
		return ResponseEntity.ok(currentCart);
	}
}
