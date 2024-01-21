package com.thuongmaidientu.controller.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.thuongmaidientu.model.Product;
import com.thuongmaidientu.model.Report;
import com.thuongmaidientu.service.ProductService;
import com.thuongmaidientu.service.ReportService;

@Controller
@RequestMapping("admin")
public class AReport {
	@Autowired
	private ReportService reportService;
	@Autowired
	private ProductService productService;
	
	@GetMapping("/report")
	public String indexReport(Model model) {
		
		
		
		 List<Object[]> reportCountsByProduct = reportService.countReportsByProduct();
		
		model.addAttribute("reportCountsByProduct", reportCountsByProduct);
		return "admin/report";
	}
	
	@GetMapping("/infor")
	public String  inforReport(@RequestParam(name = "productId") Long productId, Model model){
		try {
			
			Product product = productService.findById(productId);
			
			List<Report> reports = reportService.findByProduct(product);
			
			model.addAttribute("listReport", reports);
			
			 return "/admin/modal-infor-report"; 
		} catch (Exception e) {
			 return "your-template"; 
		}
	}
	
}
