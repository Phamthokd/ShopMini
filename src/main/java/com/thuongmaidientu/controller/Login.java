package com.thuongmaidientu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.thuongmaidientu.model.User;
import com.thuongmaidientu.service.UserService;

@Controller
public class Login {
	@Autowired
	private UserService userService;
		
	@RequestMapping("/login")
	public String logon() {
		return ("/web/login");
	}
	
	@PostMapping("/add-account")
	public String createAccount(@RequestParam("userName") String userName,@RequestParam("password") String password
			,RedirectAttributes redirectAttributes) {
		try {
			User user = new User();
			user.setUserName(userName);	
			user.setPassword(password);
			
			if (userService.findByUserName(userName) != null ){
				redirectAttributes.addFlashAttribute("errorMessage", "Tên đăng nhập đã được sử dụng! Vui lòng nhập tên khác");
		    	return "redirect:/login";
	        }
			
			userService.create(user);
			redirectAttributes.addFlashAttribute("successMessage", "Đăng ký thành công. Vui lòng đăng nhập để tiếp tục");			
			return "redirect:/login";
		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("message", e);	
			return "redirect:/login";
		}
	}
	
}
