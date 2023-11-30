package com.thuongmaidientu.controller.shop;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.thuongmaidientu.model.Order;
import com.thuongmaidientu.service.OrderService;

@Controller
@RequestMapping("shop")
public class SOrder {
	@Autowired
	private OrderService orderService;
	@GetMapping("/order")
	public String orderIndexShop(@AuthenticationPrincipal UserDetails userDetails,Model model) {
		String username = userDetails.getUsername();
		List<Order> orders = orderService.findOrdersByProductSupplier(username);
		
		model.addAttribute("listOrders", orders);
		return "/vendor/order";
	}
}
