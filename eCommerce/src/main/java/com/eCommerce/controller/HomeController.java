package com.eCommerce.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.eCommerce.constant.SessionConstant;
import com.eCommerce.entity.Products;
import com.eCommerce.entity.Users;
import com.eCommerce.service.ProductsService;
import com.eCommerce.service.UsersService;

@Controller
public class HomeController {

	@Autowired
	private ProductsService productsService;
	
	@Autowired
	private UsersService usersService;
	
	private static final int PAGE_SIZE_MAX=8;
	
	@GetMapping(value= {"/index","","/"})
	public String doGetIndex(@RequestParam(value="page", required = false, defaultValue = "1") int page,Model model) {

		List<Products> products = new ArrayList<>();
		if( page ==1 ) {
			try {
				Page<Products> pageProducts=productsService.findAll(PAGE_SIZE_MAX, 1);
				products=pageProducts.getContent();
				model.addAttribute("totalPages", pageProducts.getTotalPages());
				model.addAttribute("currentPage", page);
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		try {
			Page<Products> pageProducts=productsService.findAll(PAGE_SIZE_MAX, page);
			products=pageProducts.getContent();
			model.addAttribute("totalPages", pageProducts.getTotalPages());
			model.addAttribute("currentPage", page);
		}catch(Exception e) {
			e.printStackTrace();
		}
		model.addAttribute("products", products);
		return "/index";
	}
	
	@GetMapping("/login")
	public String doGetLogin(Model model) {
		model.addAttribute("userRequest", new Users());
		return "/login";
	}
	
	@GetMapping("/logout")
	public String doGetLogout(HttpSession session) {
		session.removeAttribute(SessionConstant.CURRENT_USER);
		return "redirect:/index";
	}
	
	@GetMapping("/register")
	public String doGetRegister(Model model) {
		model.addAttribute("userRequest", new Users());
		return "register";
	}
	
	
		
	@PostMapping("/login")
	public String doPostLogin(@ModelAttribute("userRequest") Users userRequest,
			HttpSession session) {
		
		Users userResponse=usersService.doLogin(userRequest.getUsername(), userRequest.getHashPassword());
		if(userResponse!=null) {
			session.setAttribute(SessionConstant.CURRENT_USER, userResponse);
			return "redirect:/index";
		}else {
			return "redirect:/login";
		}
	}
	
	@PostMapping("/register")
	public String doPostRegister(@ModelAttribute("userRequest") Users userRequest,
			HttpSession session) {
		try {
			Users userResponse = usersService.save(userRequest);
			if(userResponse!=null) {
				session.setAttribute(SessionConstant.CURRENT_USER, userResponse);
				return "redirect:/index";
			}else {
				return "redirect:/register";
			}
		}catch(Exception e) {
			e.printStackTrace();
			return "redirect:/register"; 
		}
		
		
	}
	
	 
}
