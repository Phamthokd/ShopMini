package com.thuongmaidientu.service;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.ui.Model;

import com.thuongmaidientu.model.Cart;
import com.thuongmaidientu.model.Product;
import com.thuongmaidientu.model.User;

public interface CartService {
	List<Cart> getAll();
	Cart create(Cart cart);
	Cart update(Cart cart);
	Boolean delete(Long id);
	Cart findById(Long id);	
	List<Cart> findByUserAndStatus(User user, String status);
	Cart processAddCart(Long id, int quantity, UserDetails userDetails,Cart cart);
	Cart findByProductAndUser(Product product, User user);
	
	void getCart(UserDetails userDetails,Model model);
	
}
