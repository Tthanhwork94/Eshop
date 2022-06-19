package com.eCommerce.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.eCommerce.constant.SessionConstant;
import com.eCommerce.dto.CartDto;
import com.eCommerce.service.CartService;
import com.eCommerce.util.SessionUtil;

@Controller
@RequestMapping("/cart")
public class CartController {
	
	@Autowired
	private CartService cartService;
	
	@GetMapping("")
	public String doGetCart(HttpSession session) {
		return "/cart";
	}
	
	//ky thuat reload fragments
	@GetMapping("/update")
	public String doGetUpdate(@RequestParam("productId") Long productId,
			@RequestParam("quantity") Integer quantity,
			@RequestParam("isReplace") Boolean isReplace,
			HttpSession session){
		CartDto currentCart=SessionUtil.getCurrentCart(session);
		cartService.updateCart(currentCart, productId, quantity, isReplace);
		return "cart::#inforCart";
	}

}
