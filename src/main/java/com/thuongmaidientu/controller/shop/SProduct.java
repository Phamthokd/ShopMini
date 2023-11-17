package com.thuongmaidientu.controller.shop;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.thuongmaidientu.model.Category;
import com.thuongmaidientu.model.Product;
import com.thuongmaidientu.model.User;
import com.thuongmaidientu.service.CategoryService;
import com.thuongmaidientu.service.ProductService;
import com.thuongmaidientu.service.StorageService;
import com.thuongmaidientu.service.UserService;

@Controller
@RequestMapping("shop")
public class SProduct {
	@Autowired
	private ProductService productService;

	@Autowired
	private CategoryService categoryService;

	@Autowired
	private StorageService storageService;

	@Autowired
	private UserService userService;

	@GetMapping("/product")
	public String ProductHome(@AuthenticationPrincipal UserDetails userDetails,Model model) {
		
		String username = userDetails.getUsername();
		User user = this.userService.findByUserName(username);		
		List<Product> listProducts = this.productService.findByUserProduct(user);
		model.addAttribute("product", listProducts);
		return "/vendor/product";
	}

	@RequestMapping("/add-product")
	public String ProductAdd(Model model) {
		Product product = new Product();
		model.addAttribute("productObj", product);
		return "/vendor/addProduct";
	}

	@PostMapping("/add-product")
	public String createProduct(@AuthenticationPrincipal UserDetails userDetails,@ModelAttribute("productObj") Product product,
			@RequestParam("fileImgage1") MultipartFile file1, @RequestParam("fileImgage2") MultipartFile file2,
			@RequestParam("fileImgage3") MultipartFile file3, @RequestParam("fileImgage4") MultipartFile file4,
			@RequestParam("categoryInput") String categoryInput, @RequestParam("description") String description,
			@RequestParam("shortDescription") String shortDescription) {
		
		productService.processFile(file1, file2, file3, file4,description,shortDescription,product);
					
		Category category = categoryService.findByName(categoryInput);	
		productService.processCategory(category,categoryInput ,product);

		String username = userDetails.getUsername();
		User user = this.userService.findByUserName(username);
		product.setUserProduct(user);
				
		if (productService.create(product) != null) {
			return "redirect:/shop/product";
		} else {
			return "redirect:/shop/product/error";
		}

	}

	@RequestMapping("/edit-product/{id}")
	public String productEdit(Model model, @PathVariable("id") Long id) {
		Product product = this.productService.findById(id);
		model.addAttribute("product", product);
		model.addAttribute("productObj", product);
		return "/vendor/editProduct";
	}

	@PostMapping("/edit-product")
	public String updateProduct(@AuthenticationPrincipal UserDetails userDetails,@ModelAttribute("productObj") Product product,
			@RequestParam("fileImgage1") MultipartFile file1, @RequestParam("fileImgage2") MultipartFile file2,
			@RequestParam("fileImgage3") MultipartFile file3, @RequestParam("fileImgage4") MultipartFile file4,
			@RequestParam("categoryInput") String categoryInput, @RequestParam("description") String description,
			@RequestParam("shortDescription") String shortDescription) {
		
		productService.processFileEdit(file1, file2, file3, file4, description, shortDescription, product);
		
		Category category = categoryService.findByName(categoryInput);

		productService.processCategory(category, categoryInput, product);

		String username = userDetails.getUsername();
		User user = this.userService.findByUserName(username);
		product.setUserProduct(user);
		
		if (productService.update(product) != null) {
			return "redirect:/shop/product";
		} else {
			return "redirect:/shop/product/error2";
		}

	}
	
	@RequestMapping("/delete-product/{id}")
	public String deleteProduct(@PathVariable("id") Long id ) {		
		if(this.productService.delete(id)) {
			return "redirect:/shop/product";
		}else {
			return "redirect:/shop/product";
		}	
	}

}
