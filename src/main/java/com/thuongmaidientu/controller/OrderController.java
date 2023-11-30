package com.thuongmaidientu.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.thuongmaidientu.model.Cart;
import com.thuongmaidientu.model.Order;
import com.thuongmaidientu.model.User;
import com.thuongmaidientu.service.CartService;
import com.thuongmaidientu.service.OrderService;
import com.thuongmaidientu.service.UserService;

@Controller
public class OrderController {
	
	@Autowired
	private CartService cartService;
	
	@Autowired 
	private UserService userService;
	
	@Autowired
	private OrderService orderService;
	
	@GetMapping("/checkout")
	public String checkoutIndex(Model model,@AuthenticationPrincipal UserDetails userDetails) {
		
		User user = userService.findByUserName(userDetails.getUsername());
		
		
		
		if(userDetails != null) {
			cartService.getCart(userDetails, model);
		}
		
		model.addAttribute("user", user);
		return "web/checkout";
	}
	@PostMapping("/add-order")
	public String orderAdd(@RequestParam("CustomerName") String CustomerName,@RequestParam("CustomerAddress") String CustomerAddress,
			@RequestParam("CustomerPhone") String CustomerPhone,@RequestParam("CustomerNote") String CustomerNote,
			@AuthenticationPrincipal UserDetails userDetails,Model model) {
		
		User user = userService.findByUserName(userDetails.getUsername());
		List<Cart> carts = cartService.findByUserAndStatus(user, "Giỏ hàng");	 
		Order order = orderService.create(user, carts, CustomerName, CustomerAddress, CustomerPhone, CustomerNote);
		return "redirect:/order";
	}
	
	@RequestMapping("/order")
	public String orderIndex(Model model,@AuthenticationPrincipal UserDetails userDetails) {
		
		User user = userService.findByUserName(userDetails.getUsername());
		List<Order> orders = orderService.findByUserAndStatus(user, "Đã nhận đơn hàng");
				
		if(userDetails != null) {
			cartService.getCart(userDetails, model);
		}
		
		model.addAttribute("listOrders", orders);
		return "web/order";
	}
}
