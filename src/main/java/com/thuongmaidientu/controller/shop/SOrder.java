package com.thuongmaidientu.controller.shop;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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

import com.thuongmaidientu.model.Order;
import com.thuongmaidientu.model.OrderDetails;
import com.thuongmaidientu.service.OrderDetailsService;
import com.thuongmaidientu.service.OrderService;
import com.thuongmaidientu.service.UserService;

@Controller
@RequestMapping("/shop")
public class SOrder {
	@Autowired
	private OrderService orderService;

	@Autowired
	private OrderDetailsService orderDetailsService;

	@Autowired
	private UserService userService;

	@GetMapping("/order")
	public String orderIndexShop(@AuthenticationPrincipal UserDetails userDetails, Model model) {
		String username = userDetails.getUsername();

		List<Order> listOrder = orderService
				.findOrdersWithDetailsInProcessingStatus(userService.findByUserName(username).getId(), "Có đơn hàng");

		List<Order> listProcessing = orderService
				.findOrdersWithDetailsInProcessingStatus(userService.findByUserName(username).getId(), "Chuẩn bị hàng");

		List<Order> listShipping = orderService
				.findOrdersWithDetailsInProcessingStatus(userService.findByUserName(username).getId(), "Đang giao");
		
		List<Order> listShiped = orderService
				.findOrdersWithDetailsInProcessingStatus(userService.findByUserName(username).getId(), "Chờ xác nhận");
		
		List<Order> listSuccess = orderService
				.findOrdersWithDetailsInProcessingStatus(userService.findByUserName(username).getId(), "Hoàn thành");
		
		model.addAttribute("listOrder", listOrder);
		model.addAttribute("listProcessing", listProcessing);
		model.addAttribute("listShipping", listShipping);
		model.addAttribute("listShiped", listShiped);
		model.addAttribute("listSuccess", listSuccess);
		
		model.addAttribute("userName", username);
		

		return "/vendor/order";
	}


	@PostMapping("/update-order/{id}")
	public String updateOrder(@PathVariable Long id, @RequestParam String status, RedirectAttributes redirectAttributes,
			@AuthenticationPrincipal UserDetails userDetails) {
		String username = userDetails.getUsername();
		/* Order order = orderService.findById(id); */
		List<OrderDetails> listOrderDetail = orderDetailsService.findOrderDetailsByOrderIdAndUserName(id, username);

		if (status.equals("Có đơn hàng")) {
			for (OrderDetails orderDetails : listOrderDetail) {
				orderDetails.setStatus("Chuẩn bị hàng");
				orderDetailsService.save(orderDetails);
							
			}
			redirectAttributes.addFlashAttribute("messageOrder", "Đã chuẩn bị hàng");
			return "redirect:/shop/order";
		} else if (status.equals("Chuẩn bị hàng")) {
			for (OrderDetails orderDetails : listOrderDetail) {
				orderDetails.setStatus("Đang giao");
				orderDetailsService.save(orderDetails);				
			}
			redirectAttributes.addFlashAttribute("messageOrder", "Tiến hành giao hàng");
			return "redirect:/shop/order";
		} else if (status.equals("Đang giao")) {
			for (OrderDetails orderDetails : listOrderDetail) {
				orderDetails.setStatus("Chờ xác nhận");
				orderDetailsService.save(orderDetails);				
			}
			redirectAttributes.addFlashAttribute("messageOrder", "Chờ xác nhận");
			return "redirect:/shop/order";
		} else if (status.equals("Hoàn thành")) {
			for (OrderDetails orderDetails : listOrderDetail) {
				orderDetails.setStatus("Đơn hàng thành công");
				orderDetailsService.save(orderDetails);			
			}		
			redirectAttributes.addFlashAttribute("messageOrder", "Đơn hàng thành công");
			return "redirect:/shop/order";
		}
		return "redirect:/shop/order/error";
	}
}
