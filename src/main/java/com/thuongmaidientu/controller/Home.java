package com.thuongmaidientu.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.thuongmaidientu.model.Product;
import com.thuongmaidientu.service.ProductService;
@Controller
public class Home {
	@Autowired
	private ProductService productService;
	
	@GetMapping("")
	public String home(Model model) {
		List<Product> product = productService.getAll();
		model.addAttribute("product", product);
		return "web/home";
	}
	@RequestMapping("/product-details/{id}")
	private String productDetails(Model model, @PathVariable("id") Long id) {
		Product product = productService.findById(id);
		List<Product> listProducts = productService.getAll();
		model.addAttribute("product", product);
		model.addAttribute("listProduct", listProducts);
		return "web/product-details";
	}
}
