package com.thuongmaidientu.controller.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.thuongmaidientu.model.OrderDetails;
import com.thuongmaidientu.model.User;
import com.thuongmaidientu.service.OrderDetailsService;
import com.thuongmaidientu.service.UserService;

@Controller
@RequestMapping("admin")
public class AShop {
	@Autowired
	private UserService userService;
	
	@Autowired
	private OrderDetailsService orderDetailsService;
	
	@GetMapping("/seller")
	public String shopHome(Model model) {
		
		List<Object[]> infoShop = userService.infoShop();
		model.addAttribute("infoShop", infoShop);
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
	
	@GetMapping("/info-shop")
	public String  infoShop(@RequestParam(name = "shopId") Long shopId, Model model){
		try {
			User shop = userService.findById(shopId);
			model.addAttribute("shop", shop);			
			return "/admin/modal-info-shop"; 
		} catch (Exception e) {
			 return "your-template"; 
		}
	}
	
	@GetMapping("/view-order-shop")
	public String  viewOrderShop(@RequestParam(name = "shopId") Long shopId, Model model){
		try {
			User shop = userService.findById(shopId);
			
			List<OrderDetails> orderDetails = orderDetailsService.findOrderDetailsByuserName(shop.getUserName());
			
			model.addAttribute("ListOrder", orderDetails);
			
						
			return "/admin/modal-view-order-shop"; 
		} catch (Exception e) {
			 return "your-template"; 
		}
	}
	
	
}
