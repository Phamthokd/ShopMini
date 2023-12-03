package com.thuongmaidientu.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.thuongmaidientu.model.Cart;
import com.thuongmaidientu.model.Order;
import com.thuongmaidientu.model.OrderDetails;
import com.thuongmaidientu.model.Product;
import com.thuongmaidientu.model.User;
import com.thuongmaidientu.service.CartService;
import com.thuongmaidientu.service.OrderDetailsService;
import com.thuongmaidientu.service.OrderService;
import com.thuongmaidientu.service.ProductService;
import com.thuongmaidientu.service.UserService;

@Controller
public class OrderController {
	
	@Autowired
	private CartService cartService;
	
	@Autowired 
	private UserService userService;
	
	@Autowired
	private OrderService orderService;
	
	@Autowired
	private OrderDetailsService orderDetailsService;
	
	@Autowired 
	private ProductService productService;
	
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
		
		List<OrderDetails> orderShipping = orderDetailsService.findByOrder_user_UserNameAndStatus(userDetails.getUsername(), "Đang giao");
		List<OrderDetails> ordersList = orderDetailsService.findByOrder_user_UserNameAndStatus(userDetails.getUsername(), "Có đơn hàng");
		List<OrderDetails> orderProccessing = orderDetailsService.findByOrder_user_UserNameAndStatus(userDetails.getUsername(), "Chuẩn bị hàng");
		List<OrderDetails> orderConfirm = orderDetailsService.findByOrder_user_UserNameAndStatus(userDetails.getUsername(), "Chờ xác nhận");
		List<OrderDetails> orderCancelled = orderDetailsService.findByOrder_user_UserNameAndStatus(userDetails.getUsername(), "Đã hủy");
				
		if(userDetails != null) {
			cartService.getCart(userDetails, model);
		}
		model.addAttribute("ordersList", ordersList);
		model.addAttribute("orderShipping", orderShipping);
		model.addAttribute("orderProccessing", orderProccessing);
		model.addAttribute("orderConfirm", orderConfirm);
		model.addAttribute("orderCancelled", orderCancelled);
		return "web/order";
	}
	
	@PostMapping("/update-order/{id}")
	public String updateOrder(@PathVariable Long id, @RequestParam String status,
			RedirectAttributes redirectAttributes) {
		OrderDetails orderDetails = orderDetailsService.findById(id);

		if ("Chờ xác nhận".equals(status)) {
			orderDetails.setStatus("Hoàn thành");
			redirectAttributes.addFlashAttribute("messageOrder", "Cảm ơn bạn đã tin tưởng website.");
		} 		
		orderDetails = orderDetailsService.save(orderDetails);

		if (orderDetails == null) {
			redirectAttributes.addFlashAttribute("messageOrder", "Cập nhật thất bại");
			return "redirect:/order";
		}

		return "redirect:/order";
	}
	
	@PostMapping("/cancel-order")
	private ResponseEntity<String> cancelOrder( @RequestParam("id") Long id,  @AuthenticationPrincipal UserDetails userDetails) {
	    try {
	    	OrderDetails orderDetails = orderDetailsService.findById(id);
	    	orderDetails.setStatus("Đã hủy");
	    	if(orderDetailsService.save(orderDetails)!= null) {
	    		return new ResponseEntity<>("cancelSuccess", HttpStatus.OK);
	    	}
	    	return new ResponseEntity<>("cancelFalse", HttpStatus.OK);

		} catch (Exception e) {
			 return new ResponseEntity<>("error", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		
	
		
	   
		
	}
	
	
}
