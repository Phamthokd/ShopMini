package com.thuongmaidientu.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.thuongmaidientu.model.Category;
import com.thuongmaidientu.model.Product;
import com.thuongmaidientu.service.CartService;
import com.thuongmaidientu.service.CategoryService;
import com.thuongmaidientu.service.ProductService;
import com.thuongmaidientu.service.UserService;
@Controller
public class Home {
	@Autowired
	private ProductService productService;
	@Autowired
	private CategoryService categoryService;
	@Autowired
	private UserService userService;
	@Autowired
	private CartService cartService;
	
	@GetMapping("")
	public String home(Model model , @AuthenticationPrincipal UserDetails userDetails) {
		List<Category> categories = categoryService.getAll();
				
		
		List<Product> product = productService.getAll();
		List<Product> product1 = productService.findByCategory(categoryService.findByName("Thời trang nam"));
		List<Product> product2 = productService.findByCategory(categoryService.findByName("Thời trang nữ"));
		List<Product> product3 = productService.findByCategory(categoryService.findByName("Bách hóa online"));
		List<Product> product4 = productService.findByCategory(categoryService.findByName("Nhà cửa và đời sống"));
		
		model.addAttribute("category", categories);
		model.addAttribute("product", product);
		model.addAttribute("product1", product1);
		model.addAttribute("product2", product2);
		model.addAttribute("product3", product3);
		model.addAttribute("product4", product4);
		if(userDetails != null) {
			cartService.getCart(userDetails, model);
		}
		
		return "web/home";
	}
	@RequestMapping("/product-details/{id}")
	private String productDetails(Model model, @PathVariable("id") Long id,@AuthenticationPrincipal UserDetails userDetails) {
		
		Product product = productService.findById(id);
		List<Product> listProducts = productService.getAll();
		model.addAttribute("product", product);
		model.addAttribute("listProduct", listProducts);
		
		if(userDetails != null) {
			cartService.getCart(userDetails, model);
		}
		
		return "web/product-details";
	}
	
	@RequestMapping("/category")
	private String category(Model model,@Param("keyword") String keyword,@RequestParam(name = "pageNo",defaultValue = "1") Integer pageNo,
			@AuthenticationPrincipal UserDetails userDetails) {	
		Page<Product> product = productService.getAll(pageNo);
		
		if(keyword != null) {
			product = productService.searchProducts(keyword,pageNo);
		}
		
		model.addAttribute("keyword", keyword);
		model.addAttribute("totalPage", product.getTotalPages());
		model.addAttribute("currentPage", pageNo);
		model.addAttribute("product", product);
		if(userDetails != null) {
			cartService.getCart(userDetails, model);
		}
			
		return "web/shop-fullwidth-list";
	}
	
	@RequestMapping("/category/{id}")
	private String categoryId(Model model,@PathVariable("id") Long id,@RequestParam(name = "pageNo",defaultValue = "1") Integer pageNo,
			@AuthenticationPrincipal UserDetails userDetails) {	
		Page<Product> product = productService.getAll(pageNo);
		
		if(id != null) {
			product = productService.findByCategory(categoryService.findById(id), pageNo);
		}
		
		model.addAttribute("totalPage", product.getTotalPages());
		model.addAttribute("currentPage", pageNo);
		
		model.addAttribute("product", product);
		
		if(userDetails != null) {
			cartService.getCart(userDetails, model);
		}
		
		return "web/shop-fullwidth-list";
	}
	
	
	
	@RequestMapping("/seller/{id}")
	private String seller(Model model,@PathVariable("id") Long id,@RequestParam(name = "pageNo",defaultValue = "1") Integer pageNo,
			@AuthenticationPrincipal UserDetails userDetails) {
		Page<Product> product = productService.getAll(pageNo);
		if(id != null) {
			product = productService.findByUserProduct(userService.findById(id), pageNo);
		}
		
		model.addAttribute("totalPage", product.getTotalPages());
		model.addAttribute("currentPage", pageNo);
		
		model.addAttribute("product", product);
		
		if(userDetails != null) {
			cartService.getCart(userDetails, model);
		}
		return "web/shop-fullwidth-list";
	}
	
}
