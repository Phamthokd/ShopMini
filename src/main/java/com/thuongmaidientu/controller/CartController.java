package com.thuongmaidientu.controller;

import org.springframework.beans.factory.annotation.Autowired;
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

import com.thuongmaidientu.model.Cart;
import com.thuongmaidientu.model.Product;
import com.thuongmaidientu.model.User;
import com.thuongmaidientu.service.CartService;
import com.thuongmaidientu.service.ProductService;
import com.thuongmaidientu.service.UserService;

@Controller
public class CartController {
	@Autowired
	private CartService cartService;
	@Autowired
	private UserService userService;
	@Autowired
	private ProductService productService;
	
	@GetMapping("/cart")
	public String CartIndex(@AuthenticationPrincipal UserDetails userDetails,Model model) {
		
		cartService.getCart(userDetails, model);
		return "web/cart";
		
	}
	
	@PostMapping("/add-cart/{id}")
	public ResponseEntity<String> addCart(
	    @PathVariable("id") Long id,
	    @RequestParam("squantity") int squantity,
	    @AuthenticationPrincipal UserDetails userDetails) {		
		try {
	        User user = userService.findByUserName(userDetails.getUsername());
	        Product product = productService.findById(id);
	        Cart existingCart = cartService.findByProductAndUser(product, user);

	        if (existingCart != null && "Giỏ hàng".equals(existingCart.getStatus())) {
	            // Bản ghi đã tồn tại, trả về một thông báo        	
	            return new ResponseEntity<>("CartEntryExists", HttpStatus.OK);
	        } else {
	            Cart cart = new Cart();
	            cart = cartService.processAddCart(id, squantity, userDetails, cart);
	            this.cartService.create(cart);
	            return new ResponseEntity<>("CartEntryAdded", HttpStatus.OK);
	        } 	  
	    } catch (Exception e) {	        
	    	 return new ResponseEntity<>("Error", HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	}
	
	@RequestMapping("/delete-cart/{id}")
	public String deleteCart(@PathVariable("id") Long id ) {		
		if(this.cartService.delete(id)) {
			return "redirect:/cart";
		}else {
			return "redirect:/cart";
		}	
	}
	
	
	
}
