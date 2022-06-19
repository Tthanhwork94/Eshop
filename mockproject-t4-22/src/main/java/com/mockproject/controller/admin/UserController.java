package com.mockproject.controller.admin;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.mockproject.entity.Users;
import com.mockproject.service.UsersService;

@Controller
@RequestMapping("/admin/user")
public class UserController {
	
	@Autowired
	private UsersService usersService;
	
	@GetMapping("")
	public String doGetIndex(Model model) {
		List<Users> users = usersService.findAll();
		model.addAttribute("users", users);
		model.addAttribute("userRequest",new Users());
		return "/admin/user";
	}
	
	@GetMapping("/delete")
	public String doGetDelete(@RequestParam("username") String username,
	RedirectAttributes redirectAttributes) {
		try {
			usersService.deleteLogical(username);
			redirectAttributes.addFlashAttribute("succeedMessage", "User "+username+" was deleted");
		}catch (Exception e) {
			e.printStackTrace();
			redirectAttributes.addFlashAttribute("errorMessage", "Cannot delete User "+username);
		}
		return "redirect:/admin/user";
	}
	
	@GetMapping("/edit")
	public String doGetEditUser(@RequestParam("username") String username,Model model) {
		Users userRequest = usersService.findByUsername(username);
		model.addAttribute("userRequest", userRequest);
		return "admin/user::#form";
	}
	
	@PostMapping("/create")
	public String doPostCreateUser(@ModelAttribute("userRequest") Users userRequest,
			RedirectAttributes redirectAttributes) {
		
		try {
			usersService.save(userRequest);
			redirectAttributes.addFlashAttribute("succeedMessage", "User "+userRequest.getUsername());
			
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			redirectAttributes.addFlashAttribute("errorMessage", "Cannot create User "+userRequest.getUsername());
		}
		return "redirect:/admin/user";
	}
	
	@PostMapping("/edit")
	public String doPostEditUser(@Valid @ModelAttribute("userRequest") Users userRequest,
			BindingResult bindingResult,
			RedirectAttributes redirectAttributes) {
		
		if (bindingResult.hasErrors()) {
			redirectAttributes.addFlashAttribute("errorMessage", "user is not valid");
		}else  {
			try {
				usersService.update(userRequest);
				redirectAttributes.addFlashAttribute("succeedMessage", "Edit User "+userRequest.getUsername()+" successfully");
			}catch (Exception e) {
				e.printStackTrace();
				redirectAttributes.addFlashAttribute("errorMessage", "Cannot edit User "+userRequest.getUsername());
			}
			
		}
		
		return "redirect:/admin/user";
	}
}
