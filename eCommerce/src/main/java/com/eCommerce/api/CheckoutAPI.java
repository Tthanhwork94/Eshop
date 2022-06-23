package com.eCommerce.api;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.eCommerce.constant.SessionConstant;
import com.eCommerce.dto.CartDto;
import com.eCommerce.entity.Users;
import com.eCommerce.service.CheckoutService;
import com.eCommerce.util.SessionUtil;

@RestController
@RequestMapping("/api/checkout")
public class CheckoutAPI {
	
	@Autowired
	private CheckoutService checkoutService;

	@GetMapping("")
	public ResponseEntity<?> doGetCheckout(
			@Valid @NotBlank @RequestParam("address") String address,
			@Valid @NotBlank @RequestParam("phone") String phone,
			HttpSession session){
		if(ObjectUtils.isEmpty(address) && ObjectUtils.isEmpty(address)) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		Users currentUser=(Users) session.getAttribute(SessionConstant.CURRENT_USER);
		if(!ObjectUtils.isEmpty(currentUser)) {
			CartDto currentCart=SessionUtil.getCurrentCart(session);
			try {
				checkoutService.insert(currentCart, currentUser, address, phone);
				session.setAttribute(SessionConstant.CURRENT_CART, new CartDto());
				return new ResponseEntity<>(HttpStatus.OK);
			}catch(Exception e) {
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			}
		}else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
	}
}
