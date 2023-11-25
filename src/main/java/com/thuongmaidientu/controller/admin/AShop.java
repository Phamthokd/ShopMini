package com.thuongmaidientu.controller.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.thuongmaidientu.model.User;
import com.thuongmaidientu.service.UserService;

@Controller
@RequestMapping("admin")
public class AShop {
	@Autowired
	private UserService userService;
	
	@GetMapping("/seller")
	public String shopHome(Model model) {
		List<User> shop = userService.findByRole("SHOP");
		model.addAttribute("shop", shop);
		return "admin/shop";
	}
	
	@RequestMapping("/delete-shop/{id}")
	public String deleteProduct(@PathVariable("id") Long id ) {		
		if(this.userService.delete(id)) {
			return "redirect:/admin/seller";
		}else {
			return "redirect:/admin/seller1";
		}	
	}
}
