package com.thuongmaidientu.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.thuongmaidientu.service.OrderService;
import com.thuongmaidientu.service.ProductService;
import com.thuongmaidientu.service.UserService;

@Controller
@RequestMapping("/admin")
public class AHome {
	@Autowired
	private OrderService orderService;
	
	@Autowired
	private UserService userService;
	@Autowired
	private ProductService productService;
	
	@GetMapping
	public String indexAdmin() {
		return ("redirect:/admin/");
	}
	
	@RequestMapping("/")
	public String adminHome(Model model) {
		
		Long countOrder = orderService.countOrder();
		Long countCustomer = userService.countByRole("CUSTOMER");
		Long countShop = userService.countByRole("SHOP");
		Long countProduct = productService.countProduct();
		
		model.addAttribute("countOrder", countOrder);
		model.addAttribute("countCustomer", countCustomer);
		model.addAttribute("countShop", countShop);
		model.addAttribute("countProduct", countProduct);
		return "admin/home";
	}
}
