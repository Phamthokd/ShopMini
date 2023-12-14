package com.thuongmaidientu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.thuongmaidientu.model.Cart;
import com.thuongmaidientu.model.Order;
import com.thuongmaidientu.model.Product;
import com.thuongmaidientu.model.User;
import com.thuongmaidientu.service.CartService;
import com.thuongmaidientu.service.OrderDetailsService;
import com.thuongmaidientu.service.OrderService;
import com.thuongmaidientu.service.ProductService;
import com.thuongmaidientu.service.UserService;

@Controller
@CrossOrigin(origins = "http://localhost:8080")
public class CartController {
	@Autowired
	private CartService cartService;
	@Autowired
	private UserService userService;
	@Autowired
	private OrderService orderService;

	@Autowired
	private ProductService productService;
	@Autowired
	private OrderDetailsService orderDetailsService;

	@GetMapping("/cart")
	public String CartIndex(@AuthenticationPrincipal UserDetails userDetails, Model model) {

		cartService.getCart(userDetails, model);
		return "web/cart";

	}

	@PostMapping("/add-cart")
	public ResponseEntity<String> addCart(@RequestParam("quantity") int quantity, @RequestParam("id") Long id,
			@AuthenticationPrincipal UserDetails userDetails) {
		try {
			User user = userService.findByUserName(userDetails.getUsername());

			Product product = productService.findById(id);

			Cart cartCheck = cartService.findByProductAndUserAndStatus(product, user, "Giỏ hàng");

			if (cartCheck != null && "Giỏ hàng".equals(cartCheck.getStatus())) {
				// Bản ghi đã tồn tại, trả về một thông báo
				return new ResponseEntity<>("CartEntryExists", HttpStatus.OK);
			} else {
				Cart cart = new Cart();
				cart = cartService.processAddCart(id, quantity, user, cart);
				this.cartService.create(cart);
				return new ResponseEntity<>("CartEntryAdded", HttpStatus.OK);
			}

		} catch (Exception e) {
			return new ResponseEntity<>("Error", HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}
	
	@PostMapping("/buy-again")
	public String buyAgain(
	        @RequestParam("quantity") int quantity,
	        @RequestParam("id") Long id,
	        @RequestParam("idOrderDetail") Long idOrderDetail,
	        @AuthenticationPrincipal UserDetails userDetails) {
	    try {
	        User user = userService.findByUserName(userDetails.getUsername());
	        Product product = productService.findById(id);

	        // Kiểm tra và cập nhật giỏ hàng nếu sản phẩm đã có trong giỏ hàng
	        Cart cartCheck = cartService.findByProductAndUserAndStatus(product, user, "Giỏ hàng");
	        if (cartCheck != null && "Giỏ hàng".equals(cartCheck.getStatus())) {
	            cartCheck.setQuantity(cartCheck.getQuantity() + quantity);
	            cartCheck.setTotal(cartCheck.getProduct().getDiscount() * cartCheck.getQuantity());
	            
	            cartService.update(cartCheck);
	        } else {
	            
	            Cart cart = cartService.processAddCart(id, quantity, user, new Cart());
	            cartService.create(cart);
	        }

	       
	        Order order = orderDetailsService.findById(idOrderDetail).getOrder();
	        if (orderDetailsService.delete(idOrderDetail)) {            
	            if (!order.getOrderDetails().isEmpty() ) {	 
	            	orderService.delete(order);
	            	return "redirect:/cart";
	            }
	        }
	        return "redirect:/order";
	    } catch (Exception e) {
	        return "redirect:/cartError";
	    }
	}
	@GetMapping("/delete-cart/{id}")
	public String deleteCart(@PathVariable("id") Long id) {
		if (this.cartService.delete(id)) {
			return "redirect:/cart";
		} else {
			return "redirect:/cart";
		}
	}

}
