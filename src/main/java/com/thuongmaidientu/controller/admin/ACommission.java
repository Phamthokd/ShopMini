package com.thuongmaidientu.controller.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.thuongmaidientu.model.Commission;
import com.thuongmaidientu.service.CommissionService;

@Controller
@RequestMapping("admin")
public class ACommission {
	@Autowired
	private CommissionService commissionService;
	
	
	
	@GetMapping("/commission")
	public String commissionHome(Model model) {
		List<Commission> listCommission = commissionService.findByStatusOrStatus();	
		model.addAttribute("listCommission", listCommission);
		return "admin/commission";
	}
	
	@RequestMapping("/settlement")
	public String settlementCommission(Model model) {
		List<Commission> listCommission = commissionService.findByStatusOrStatus();
		for (Commission commission : listCommission) {
			if(commission.getStatus().equals("Đang xử lý")) {
				commission.setStatus("Chờ thanh toán");
				commissionService.update(commission);
			}
			
		}		
		model.addAttribute("listCommission", listCommission);
		return "admin/commission";
	}
	
	@RequestMapping("/commission-success/{id}")
	public String commissionSuccess(Model model, @PathVariable("id") Long id) {
		try {
			Commission commission = commissionService.findById(id);
			if(commission.getStatus().equals("Thanh toán")) {
				commission.setStatus("Xác nhận đã thanh toán");
				commissionService.update(commission);	
			}			
			return ("redirect:/admin/commission");
		} catch (Exception e) {
			return ("redirect:/admin/commission/error");
		}
		
	}
}
