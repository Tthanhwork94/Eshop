package com.eCommerce.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.eCommerce.service.OrderDetailsService;
import com.eCommerce.service.OrdersService;
import com.eCommerce.service.ProductsService;

@Controller
@RequestMapping("/checkout")
public class CheckoutController {

	@GetMapping("")
	public String doGetCheckout(HttpSession session) {
		return "checkout";
	}
	
}
