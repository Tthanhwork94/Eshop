package com.eCommerce.controller.admin;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.eCommerce.constant.SessionConstant;
import com.eCommerce.entity.Users;
import com.eCommerce.service.OrderDetailsService;
import com.eCommerce.service.OrdersService;
import com.eCommerce.service.ProductsService;
import com.eCommerce.service.UsersService;

@Controller
@RequestMapping("/admin")
public class AdminController {
	
	@Autowired
	private UsersService usersService;
	
	@Autowired
	private ProductsService productsService;
	
	@Autowired
	private OrdersService ordersService;
	
	@Autowired
	private OrderDetailsService orderDetailsService;
	
	@GetMapping(value = {"","/","/index"})
	public String doGetDashboard(Model model) {
		
		Integer numberUsers=usersService.countUsers();
		Integer numberProducts=productsService.countProducts();
		Integer numberOrders=ordersService.countOrders();
		Double sumsales=orderDetailsService.sumSales();
		model.addAttribute("numberUsers", numberUsers);
		model.addAttribute("numberProducts", numberProducts);
		model.addAttribute("numberOrders", numberOrders);
		model.addAttribute("sumsales", sumsales);
		return "/pages/dashboard";
	}
	
	
	@GetMapping("/user")
	public String doGetInforUser(HttpSession session) {
		List<Users> users=usersService.findAll();
		session.setAttribute(SessionConstant.LIST_USERS,users);
		return "pages/tables";
	}
	
	@GetMapping("/delete")
	public String doGetDelete(@Param("userId") String username,
			RedirectAttributes redirectAttributes) {
		try {
			usersService.deleteLogic(username);
			redirectAttributes.addFlashAttribute("successMessage", "User "+username+" was deleted");
			
		}catch(Exception e) {
			e.printStackTrace();
			redirectAttributes.addFlashAttribute("errorMessage", "cannot delete User: "+username);
		}
		return "redirect:/admin/user";
		
	}
	
	@GetMapping("/sign-in")
	public String doGetSignIn(Model model) {
		model.addAttribute("userRequest", new Users());
		return "pages/sign-in";
	}
	
	@GetMapping("/sign-up")
	public String doGetSignUp() {
		return "pages/sign-up";
	}
	
	@PostMapping("/sign-in")
	public String doPostSignIn(@ModelAttribute("userRequest") Users userRequest,
			HttpSession session) {
		
		Users userResponse=usersService.doLoginAdmin(userRequest.getUsername(), userRequest.getHashPassword());
		if(userResponse!=null) {
			session.setAttribute(SessionConstant.CURRENT_USER, userResponse);
			return "redirect:/admin";
		}else {
			return "redirect:/admin/sign-in";
		}
		
	}
	
}
