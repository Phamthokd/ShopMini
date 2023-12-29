package com.thuongmaidientu.controller.shop;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.thuongmaidientu.model.Product;
import com.thuongmaidientu.model.User;
import com.thuongmaidientu.service.OrderDetailsService;
import com.thuongmaidientu.service.OrderService;
import com.thuongmaidientu.service.ProductService;
import com.thuongmaidientu.service.UserService;

@Controller
@RequestMapping("shop")
public class SStatistical {
	@Autowired
	private ProductService productService;
	@Autowired
	private UserService userService;
	
	@Autowired
	private OrderDetailsService orderDetailsService;
	@Autowired
	private OrderService orderService;

	@GetMapping("/statistical")
	public String statisticalIndex(Model model,@AuthenticationPrincipal UserDetails userDetails) {
		User user = userService.findByUserName(userDetails.getUsername());
		
		List<Object[]> top3ProductsWithRevenue = productService.findTop3ProductsWithRevenueByUser(user);
		List<Object[]> top3ProductsWithRevenueASC = productService.findTop3ProductsWithRevenueByUserASC(user);
		List<Object[]> totalRevenueByDayForSeller = orderService.calculateTotalRevenueByDayForSeller(user);
		
		Double totalRevenue = orderDetailsService.getTotalRevenueByUser(user);		
		Double totalCommission = totalRevenue * 0.025;
		Long countProduct = productService.countProduct(user);
		Long countOrder = orderService.countOrder(user);
		
		List<String> productNames = new ArrayList<>();
	    List<Double> revenues = new ArrayList<>();
	    
	    List<String> productNamesLow = new ArrayList<>();
	    List<Double> revenuesLow = new ArrayList<>();
	    
	    List<Date> dayOrder = new ArrayList<>();
	    List<Double> money = new ArrayList<>();

	    for (Object[] result : top3ProductsWithRevenue) {
	        Product product = (Product) result[0];
	        Double revenue = (Double) result[1];

	        productNames.add(product.getName());
	        revenues.add(revenue);
	    }
	    
	    for (Object[] result : top3ProductsWithRevenueASC) {
	        Product product = (Product) result[0];
	        Double revenue = (Double) result[1];

	        productNamesLow.add(product.getName());
	        revenuesLow.add(revenue);
	    }
	    
	    for (Object[] result : totalRevenueByDayForSeller) {
	        Date date = (Date) result[0];
	        Double revenue = (Double) result[1];

	        dayOrder.add(date);
	        money.add(revenue);
	    }
	    
	    
	    model.addAttribute("productNamesLow", productNamesLow);
	    model.addAttribute("revenues", revenues);
	    
	    model.addAttribute("productNames", productNames);
	    model.addAttribute("quantities", revenuesLow);
	    
	    model.addAttribute("dayOrder", dayOrder);
	    model.addAttribute("money", money);
       
        model.addAttribute("top3ProductsWithRevenue", top3ProductsWithRevenue);	
        model.addAttribute("top3ProductsWithRevenueASC", top3ProductsWithRevenueASC);
        model.addAttribute("totalRevenueByDayForSeller", totalRevenueByDayForSeller);
        model.addAttribute("totalRevenue", totalRevenue);
        model.addAttribute("totalCommission", totalCommission);
        model.addAttribute("countProduct", countProduct);
        model.addAttribute("countOrder", countOrder);
        
		return "/vendor/statistical";
	}
}
