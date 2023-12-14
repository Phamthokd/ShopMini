package com.thuongmaidientu.controller.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.thuongmaidientu.model.Order;
import com.thuongmaidientu.service.OrderService;

@Controller
@RequestMapping("admin")
public class AOrder {
	@Autowired
	private OrderService orderService;
	
	
	@GetMapping("/order")
	public String aOrderIndex(Model model) {
		List<Order> ListOrder = orderService.findAll();
			
		model.addAttribute("ListOrder", ListOrder);
		
		return "admin/order";
	}
}
