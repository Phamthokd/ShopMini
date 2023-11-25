package com.thuongmaidientu.controller.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.thuongmaidientu.model.User;
import com.thuongmaidientu.service.UserService;

@Controller
@RequestMapping("admin")
public class ACustomer {
	
	@Autowired
	private UserService userService;
	
	@GetMapping("/customer")
	public String customerHome(Model model) {
		List<User> customer = userService.findByRole("CUSTOMER");
		model.addAttribute("customer", customer);
		return "admin/customer";
	}
}
