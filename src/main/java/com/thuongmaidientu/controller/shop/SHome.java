package com.thuongmaidientu.controller.shop;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/shop")
public class SHome {
	@GetMapping
	public String index() {
		return ("redirect:/shop/");
	}
	
	@RequestMapping("/")
	public String sellerHome() {
		return "vendor/home";
	}
}
