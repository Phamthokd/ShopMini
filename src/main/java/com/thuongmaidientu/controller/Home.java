package com.thuongmaidientu.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.thuongmaidientu.model.Category;
import com.thuongmaidientu.model.Product;
import com.thuongmaidientu.model.Report;
import com.thuongmaidientu.model.Review;
import com.thuongmaidientu.model.User;
import com.thuongmaidientu.service.CartService;
import com.thuongmaidientu.service.CategoryService;
import com.thuongmaidientu.service.ProductService;
import com.thuongmaidientu.service.ReportService;
import com.thuongmaidientu.service.ReviewService;
import com.thuongmaidientu.service.StorageService;
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
	@Autowired
	private ReviewService reviewService;
	@Autowired
	private ReportService reportService;
	@Autowired
	private StorageService storageService;
	
	@GetMapping("")
	public String home(Model model , @AuthenticationPrincipal UserDetails userDetails) {
		List<Category> categories = categoryService.findFirst7ByOrderByIdAsc();
				
		
		List<Product> product = productService.findAllByOrderByNumberOfViewsDesc();
		
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
		product.setNumberOfViews(product.getNumberOfViews()+1);
		productService.update(product);
		
		List<Product> listProducts = productService.findByCategory(product.getCategory());
		List<Product> productOfShop = productService.findByUserProductAndStatus(product.getUserProduct(), "Đang bán");
		
		List<Review> reviews = reviewService.findByProductId(id);
     
        model.addAttribute("reviews", reviews);        
		model.addAttribute("product", product);
		model.addAttribute("listProduct", listProducts);
		model.addAttribute("productOfShop", productOfShop);
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
	
	@PostMapping("add-review")
	public String addReview(@AuthenticationPrincipal UserDetails userDetails,@RequestParam(name = "idProduct") Long idProduct
			,@RequestParam(name = "comment") String comment,RedirectAttributes redirectAttributes) {
		try {
			Review review = new Review();	
			User userComment = userService.findByUserName(userDetails.getUsername());	
			Product productReview = productService.findById(idProduct);
			if(comment.trim().length() >0) {
				review.setProduct(productReview);
				review.setUser(userComment);
				review.setReview(comment);
				Date timestamp = new Date();
				review.setTimestamp(timestamp);		
				reviewService.create(review);
			}
					
			redirectAttributes.addAttribute("id", idProduct);
			
			return "redirect:/product-details/{id}";
		} catch (Exception e) {
			return null;

		}	
	}
	
	@PostMapping("add-report")
	public ResponseEntity<String> addReport(@RequestParam(name = "reportContent") String reportContent,
			@RequestParam(name = "idProduct") Long idProduct,@AuthenticationPrincipal UserDetails userDetails			
			){
		try {
			Report report = new Report();
			User userReport = userService.findByUserName(userDetails.getUsername());	
			Product product = productService.findById(idProduct);
			if(reportContent.trim().length() > 0) {
				report.setContentReport(reportContent);
				report.setUser(userReport);
				report.setProduct(product);
				Date timestamp = new Date();
				report.setTimes(timestamp);	
				reportService.create(report);
			}
			
			return new ResponseEntity<>("reportSuccess", HttpStatus.OK);
			
			
		} catch (Exception e) {
			 return new ResponseEntity<>("error", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@RequestMapping("add-shop")
	public String addShop(@AuthenticationPrincipal UserDetails userDetails,Model model) {
		
		if(userDetails != null) {
			cartService.getCart(userDetails, model);
		}	
		return "web/add-shop";
	}
	@PostMapping("add-shop")
	public String registerShop(@AuthenticationPrincipal UserDetails userDetails,@RequestParam("name") String name,
			@RequestParam("address") String address,@RequestParam("email") String email,@RequestParam("phone") String phone,
			@RequestParam("avatar") MultipartFile avatar,Model model,RedirectAttributes redirectAttributes) {
		try {
			User user = userService.findByUserName(userDetails.getUsername());
			
			String avatarFile = avatar.getOriginalFilename();
			boolean isEmpty = avatarFile == null || avatarFile.trim().length() == 0;
			if (!isEmpty) {
				this.storageService.store(avatar);
				user.setAvatar(avatarFile);
			}
			user.setAddress(address);
			user.setName(name);
			user.setEmail(email);
			user.setPhone(phone);
			user.setRole("SHOP");
			
			if (userService.update(user) != null) {
				redirectAttributes.addFlashAttribute("message", "Đăng ký thành công. Đăng nhập lại để bán sản phẩm của mình");	
				return "redirect:/process-logout";
			} else {		
				return "redirect:/add-shop";
			}
			
		} catch (Exception e) {
			return "redirect:/?e";
		}
		
		
	}
	
}
