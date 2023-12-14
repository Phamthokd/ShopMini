package com.thuongmaidientu.controller.admin;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.thuongmaidientu.service.OrderService;
import com.thuongmaidientu.service.UserService;

@Controller
@RequestMapping("admin")
public class AStatistical {
	@Autowired
	private UserService userService;
	@Autowired
	private OrderService orderService;
 	
	
	@GetMapping("statistical")
	public String statisticalIndexAdmin(Model model) {
		
		 List<Object[]> topSellers = userService.findTop5SellersByOrderCount();
		 Long countOrder = orderService.countOrder();
		 Long countCustomer = userService.countByRole("CUSTOMER");
		 Long countShop = userService.countByRole("SHOP");
		 Double totalcomission = orderService.calculateTotalCommission();
		 List<Object[]> totalCommissionByDay = orderService.calculateTotalCommissionByDay();
		
		 
		 List<String> productNames = new ArrayList<>();
		 List<Double> revenues = new ArrayList<>();
		 
		 List<Date> dayOrder = new ArrayList<>();
		 List<Double> commission = new ArrayList<>();
		 
		 for (Object[] result : topSellers) {
		        String product = (String) result[1];
		        Double revenue = (Double) result[3];
		        productNames.add(product);
		        revenues.add(revenue);
		    }
		 
		 for (Object[] result : totalCommissionByDay) {
		        Date day = (Date) result[0];
		        Double revenue = (Double) result[1];
		        dayOrder.add(day);
		        commission.add(revenue);
		    }
		 model.addAttribute("productNames", productNames);
		 model.addAttribute("revenues", revenues);
		 model.addAttribute("dayOrder", dayOrder);
		 model.addAttribute("commission", commission);
		 
		 model.addAttribute("countOrder", countOrder);
		 model.addAttribute("countCustomer", countCustomer);
		 model.addAttribute("countShop", countShop);
		 model.addAttribute("totalcomission", totalcomission);
		 model.addAttribute("totalCommissionByDay", totalCommissionByDay);
		 
		 model.addAttribute("topSellers", topSellers);
		 
		return "admin/statistical";
	}
}
