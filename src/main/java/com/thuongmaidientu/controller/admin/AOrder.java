package com.thuongmaidientu.controller.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.thuongmaidientu.model.Order;
import com.thuongmaidientu.model.OrderDetails;
import com.thuongmaidientu.service.OrderDetailsService;
import com.thuongmaidientu.service.OrderService;

@Controller
@RequestMapping("admin")
public class AOrder {
	@Autowired
	private OrderService orderService;
	
	@Autowired
	private OrderDetailsService orderDetailsService;
	
	
	@GetMapping("/order")
	public String aOrderIndex(Model model) {
		List<Order> ListOrder = orderService.findAll();
			
		model.addAttribute("ListOrder", ListOrder);
		
		return "admin/order";
	}
	
	@GetMapping("/viewOrder")
	public String aViewOrder(Model model, @RequestParam("orderId") Long orderId) {
		Order order = orderService.findById(orderId);		
		List<OrderDetails> orderDetails = orderDetailsService.findByOrder(order);
		
		model.addAttribute("ListOrderDetails", orderDetails);
		
		return "admin/modal-view-order";
	}
}
