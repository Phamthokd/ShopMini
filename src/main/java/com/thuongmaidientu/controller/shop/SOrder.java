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

import com.thuongmaidientu.model.OrderDetails;
import com.thuongmaidientu.service.OrderDetailsService;
import com.thuongmaidientu.service.OrderService;

@Controller
@RequestMapping("/shop")
public class SOrder {
	@Autowired
	private OrderService orderService;

	@Autowired
	private OrderDetailsService orderDetailsService;

	@GetMapping("/order")
	public String orderIndexShop(@AuthenticationPrincipal UserDetails userDetails, Model model) {
		String username = userDetails.getUsername();

		List<OrderDetails> listOrdered = orderDetailsService.findOrderDetailsByProductSupplierAndStatus(username,
				"Có đơn hàng");
		
		List<OrderDetails> listProcessing = orderDetailsService.findOrderDetailsByProductSupplierAndStatus(username,
				"Chuẩn bị hàng");
		List<OrderDetails> listShipping = orderDetailsService.findOrderDetailsByProductSupplierAndStatus(username,
				"Đang giao");
		List<OrderDetails> listShiped = orderDetailsService.findOrderDetailsByProductSupplierAndStatus(username,
				"Chờ xác nhận");
		List<OrderDetails> listSuccess = orderDetailsService.findOrderDetailsByProductSupplierAndStatus(username,
				"Hoàn thành");

		model.addAttribute("listOrders", listOrdered);	
		model.addAttribute("listProcessing", listProcessing);
		model.addAttribute("listShipping", listShipping);
		model.addAttribute("listShiped", listShiped);
		model.addAttribute("listSuccess", listSuccess);
		
		
		return "/vendor/order";
	}

	@PostMapping("/update-order/{id}")
	public String updateOrder(@PathVariable Long id, @RequestParam String status,
			RedirectAttributes redirectAttributes) {
		OrderDetails orderDetails = orderDetailsService.findById(id);

		if ("Có đơn hàng".equals(status)) {
			orderDetails.setStatus("Chuẩn bị hàng");
			redirectAttributes.addFlashAttribute("messageOrder", "Đã chuẩn bị hàng");
		} else if ("Chuẩn bị hàng".equals(status)) {
			orderDetails.setStatus("Đang giao");
			redirectAttributes.addFlashAttribute("messageOrder", "Tiến hành giao hàng");
		} else if ("Đang giao".equals(status)) {
			orderDetails.setStatus("Chờ xác nhận");
			redirectAttributes.addFlashAttribute("messageOrder", "Chờ xác nhận");
		}else if ("Hoàn thành".equals(status)) {
			orderDetails.setStatus("Đơn hàng thành công");
			redirectAttributes.addFlashAttribute("messageOrder", "Đơn hàng thành công");
		}

		orderDetails = orderDetailsService.save(orderDetails);

		if (orderDetails == null) {
			redirectAttributes.addFlashAttribute("messageOrder", "Cập nhật thất bại");
			return "redirect:/shop/order";
		}

		return "redirect:/shop/order";
	}
}
