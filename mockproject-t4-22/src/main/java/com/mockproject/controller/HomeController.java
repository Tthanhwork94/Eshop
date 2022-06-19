package com.mockproject.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mockproject.constant.SessionConstant;
import com.mockproject.entity.Products;
import com.mockproject.entity.Roles;
import com.mockproject.entity.Users;
import com.mockproject.service.ProductsService;
import com.mockproject.service.RolesService;
import com.mockproject.service.UsersService;

@Controller
public class HomeController {

	@Autowired
	private ProductsService productsService;
	
	@Autowired
	private UsersService userService;
	
	@Autowired
	private RolesService rolesService;
	
	@GetMapping(value= {"/index","/",""})
	public String doGetIndex(Model model) {
		List<Products> products = productsService.findAll();
		model.addAttribute("products",products);
		return "user/index";
	}
	
	@GetMapping("/login")
	public String doGetLogin(Model model) {
		model.addAttribute("userRequest",new Users());
		return "user/login";
	}
	
	@GetMapping("/logout")
	public String doGetLogout(HttpSession session) {
		session.removeAttribute(SessionConstant.CURRENT_USER);
		return "redirect:/index";
	}
	
	@GetMapping("/register")
	public String doGetRegister(Model model) {
		model.addAttribute("userRequest",new Users());
		return "user/register";
	}
	
	
	
	@PostMapping("/login")
	public String doPostLogin(@ModelAttribute("userRequest") Users userRequest, 
			HttpSession session) {
		Users userRespone = userService.doLogin(userRequest.getUsername(), userRequest.getHashPassword());
		if (userRespone != null) {
			session.setAttribute(SessionConstant.CURRENT_USER, userRespone);
			return "redirect:/index";
		}else {
			return "redirect:/login";
		}
	}
	
	@PostMapping("/register")
	public String doPostRegister(@ModelAttribute("userRequest") Users userRequest
			,HttpSession session) {
		try {
			Users userRespone=userService.save(userRequest);
			
			if(userRespone!=null) {
				session.setAttribute(SessionConstant.CURRENT_USER, userRespone);
				return "redirect:/index";
			}else {
				return "redirect:/register";
			}
		}catch(Exception e) {
			return "redirect:/register";
		}
		
		
		
	}
	
	
	
	
}
