package com.thuongmaidientu.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.thuongmaidientu.model.Cart;
import com.thuongmaidientu.model.Order;
import com.thuongmaidientu.model.User;
import com.thuongmaidientu.service.CartService;
import com.thuongmaidientu.service.OrderService;
import com.thuongmaidientu.service.StorageService;
import com.thuongmaidientu.service.UserService;
@Controller
public class UserController {
	
	@Autowired
	private CartService cartService;
	@Autowired
	private UserService userService;
	
	@Autowired
	private StorageService storageService;
	@Autowired
	private OrderService orderService;
	
	
	@GetMapping("/account")
	public String accountIndex(Model model,@AuthenticationPrincipal UserDetails userDetails) {
		
		User user = userService.findByUserName(userDetails.getUsername());
		
		List<Order> orders = orderService.findAll();
		if(userDetails != null) {
			cartService.getCart(userDetails, model);
		}
		model.addAttribute("listOrder", orders);
		model.addAttribute("user", user);	
		return "web/my-account";
	}
	
	@PostMapping("/edit-account")
	private String updateAccount(@AuthenticationPrincipal UserDetails userDetails,@RequestParam("name") String name,
			@RequestParam("address") String address,@RequestParam("email") String email,@RequestParam("phone") String phone,
			@RequestParam("avatar") MultipartFile avatar,RedirectAttributes redirectAttributes
			) {
		
		User user = userService.findByUserName(userDetails.getUsername());
		
		String avatarFile = avatar.getOriginalFilename();
		boolean isEmpty = avatarFile == null || avatarFile.trim().length() == 0;
		if (!isEmpty) {
			this.storageService.store(avatar);
			user.setAvatar(avatarFile);
		}
		
		user.setName(name);
		user.setAddress(address);
		user.setEmail(email);
		user.setPhone(phone);
		
		
		if (userService.update(user) != null) {
			redirectAttributes.addFlashAttribute("messageAccount", "Cập nhật thành công");
			return "redirect:/account";
		} else {
			redirectAttributes.addFlashAttribute("messageAccount", "Cập nhật thất bại");
			return "redirect:/account";
		}
	}
	
	@PostMapping("edit-password")
	private String updatePassword(Model model,@AuthenticationPrincipal UserDetails userDetails,@RequestParam("password") String password
			,@RequestParam("confirmPasswordNew") String confirmPasswordNew,@RequestParam("passwordNew") String passwordNew,RedirectAttributes redirectAttributes) {
		
		User user = userService.findByUserName(userDetails.getUsername());
		
	    // Kiểm tra mật khẩu hiện tại có đúng không
	    if (!userService.checkCurrentPassword(user, password)) {
	    	redirectAttributes.addFlashAttribute("message", "Đổi mật thất bại. Mật khẩu hiện tại không chính xác");
	    	return "redirect:/account#password";
	        }
	    
	    // Kiểm tra xác nhận mật khẩu mới
	    if (!passwordNew.equals(confirmPasswordNew)) {
	    	redirectAttributes.addFlashAttribute("message", "Đổi mật thất bại. Mật khẩu mới không trùng với mật khẩu xác nhận lại");
	    	return "redirect:/account#password";
	    }
	    
	    // Cập nhật mật khẩu mới
	    userService.updatePassword(user, passwordNew);

	 // Thêm thông báo vào RedirectAttributes
        redirectAttributes.addFlashAttribute("message", "Đổi mật khẩu thành công");
        return "redirect:/account#password";
	}
}
