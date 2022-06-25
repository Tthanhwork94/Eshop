package com.eCommerce.controller.admin;

import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ObjectUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.eCommerce.constant.SessionConstant;
import com.eCommerce.entity.Users;
import com.eCommerce.service.OrderDetailsService;
import com.eCommerce.service.OrdersService;
import com.eCommerce.service.ProductsService;
import com.eCommerce.service.StatsService;
import com.eCommerce.service.UsersService;

@Controller
@RequestMapping("/admin")
public class AdminController {
	
	private BCryptPasswordEncoder bcrypt;
	
	@Autowired
	private UsersService usersService;
	
	@Autowired
	private ProductsService productsService;
	
	@Autowired
	private OrdersService ordersService;
	
	@Autowired
	private OrderDetailsService orderDetailsService;
	
	@Autowired
	private StatsService statsService;
	
	@GetMapping(value = {"","/","/index"})
	public String doGetDashboard(Model model) {
		
		Integer numberUsers=usersService.countUsers();
		Integer numberProducts=productsService.countProducts();
		Integer numberOrders=ordersService.countOrders();
		Double sumsales=orderDetailsService.sumSales();
		String[][] chartData = statsService.getTotalPrice6LastMonth();
		model.addAttribute("numberUsers", numberUsers);
		model.addAttribute("numberProducts", numberProducts);
		model.addAttribute("numberOrders", numberOrders);
		model.addAttribute("sumsales", sumsales);
		model.addAttribute("chartData", chartData);
		return "/pages/dashboard";
	}
	
	
	@GetMapping("/user")
	public String doGetInforUser(HttpSession session) {
		List<Users> users=usersService.findAll();
		session.setAttribute(SessionConstant.LIST_USERS,users);
		return "pages/tables";
	}
	
	@GetMapping("/delete")
	public String doGetDelete(@RequestParam("username") String username,
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
	public String doGetSignUp(Model model) {
		model.addAttribute("userRequest", new Users());
		return "pages/sign-up";
	}
	
	@GetMapping("/profile")
	public String doGetProfile() {
		return "pages/profile";
	}
	
	@GetMapping("/edit")
	public String doGetEdit(@Param("username") String username,
			RedirectAttributes redirectAttributes,HttpSession session) {
		Users user=usersService.findByUsername(username);
		session.setAttribute("usernameEdit", username);
		redirectAttributes.addFlashAttribute("userEdit", user);
		redirectAttributes.addFlashAttribute("userRequest", new Users());
		return "redirect:/admin/profile";
		
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
	
	@PostMapping("/sign-up")
	public String doPostSignUp(@ModelAttribute("userRequest") Users userRequest,
			HttpSession session) {
			bcrypt = new BCryptPasswordEncoder();
		try {
			String hashPassword=bcrypt.encode(userRequest.getHashPassword());
			userRequest.setHashPassword(hashPassword);
			Users userResponse=usersService.save(userRequest);
			if(userResponse!=null) {
				session.setAttribute(SessionConstant.CURRENT_USER, userResponse);
				return "redirect:/admin";
			}else {
				return "redirect:/admin/sign-up";
			}
		}catch(Exception e) {
			e.printStackTrace();
			return "redirect:/admin/sign-up";
		}	
	}
	
	@PostMapping("/update")
	public String doPostUpdate(@Valid @ModelAttribute("userRequest") Users userRequest,
		BindingResult bindingResult,
		HttpSession session) {
		try {
			String username = (String) session.getAttribute("usernameEdit");
			userRequest.setUsername(username);
			System.out.println(username);
			System.out.println(userRequest.getUsername());
			System.out.println(userRequest.getHashPassword());
			System.out.println(userRequest.getEmail());
			usersService.update(userRequest);
			return "redirect:/admin";
		}catch(Exception e) {
			return "redirect:/admin/user";
		}
	}
	
}
