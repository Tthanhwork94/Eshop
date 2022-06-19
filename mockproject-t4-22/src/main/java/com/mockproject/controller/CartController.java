package com.mockproject.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.mockproject.dto.CartDto;
import com.mockproject.entity.Products;
import com.mockproject.service.CartService;
import com.mockproject.service.ProductsService;
import com.mockproject.util.SessionUtil;

@Controller
@RequestMapping("/cart")
public class CartController {
	
	@Autowired
	private ProductsService productsService;
	
	@Autowired
	private CartService cartService;
	
	@GetMapping("")
	public String doGetCart(Model model) {
		List<Products> products = productsService.findAll();
		model.addAttribute("products",products);
		return "user/cart";
	}
	
	@GetMapping("/update")
	public String doGetUpdate(
			@RequestParam("productId") Long productId,
			@RequestParam("quantity") Integer quantity,
			@RequestParam("isReplace") Boolean isReplace,
			HttpSession session){
		CartDto currentCart = SessionUtil.getCurrentCart(session);
		cartService.update(currentCart, productId, quantity, isReplace);
		
		return "user/cart::#viewCartFragment";
	}
}
