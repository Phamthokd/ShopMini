package com.thuongmaidientu.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AHome {
	@GetMapping
	public String indexAdmin() {
		return ("redirect:/admin/");
	}
	
	@RequestMapping("/")
	public String adminHome() {
		return "admin/home";
	}
}
