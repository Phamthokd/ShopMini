package com.thuongmaidientu.controller.shop;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.thuongmaidientu.model.Commission;
import com.thuongmaidientu.model.User;
import com.thuongmaidientu.service.CommissionService;
import com.thuongmaidientu.service.UserService;

@Controller
@RequestMapping("/shop")
public class SCommission {
	@Autowired
	private CommissionService commissionService;
	@Autowired
	private UserService userService;
	
	@GetMapping("/commission")
	public String commissionIndexShop(@AuthenticationPrincipal UserDetails userDetails, Model model) {
		List<Commission> listCommissions = commissionService.findByUserAndStatus(userService.findByUserName(userDetails.getUsername()), "Chờ thanh toán");
		model.addAttribute("listCommission", listCommissions);	
		return "vendor/commission";
	}
	
	@RequestMapping("/settlement/{id}")
	public String settlementCommission(@AuthenticationPrincipal UserDetails userDetails,Model model,@PathVariable("id") Long id) {
		Commission commission = commissionService.findById(id);
		
		commission.setStatus("Thanh toán");
		
		commissionService.update(commission);
			
		
		return "redirect:/shop/commission";
	}
	
	@RequestMapping("/commission-of-month/{month}")
	public String commissionOfMonth(@PathVariable("month") Integer month, Model model,@AuthenticationPrincipal UserDetails userDetails) {
		User user = userService.findByUserName(userDetails.getUsername());
	    List<Commission> listCommission = commissionService.findByMonthAndUser(month, user);
	    model.addAttribute("listCommission", listCommission);
	    return "vendor/commission";
	}
}
