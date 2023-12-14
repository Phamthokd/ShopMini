package com.thuongmaidientu.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.thuongmaidientu.model.Cart;
import com.thuongmaidientu.model.Product;
import com.thuongmaidientu.model.User;
import com.thuongmaidientu.repository.CartRepository;
import com.thuongmaidientu.service.CartService;
import com.thuongmaidientu.service.ProductService;
import com.thuongmaidientu.service.UserService;

@Service
public class CartServiceImpl implements CartService {
	
	@Autowired
	private ProductService productService;
	@Autowired
	private UserService userService;
	
	@Autowired
	private CartRepository cartRepository;

	@Override
	public List<Cart> getAll() {
		// TODO Auto-generated method stub
		return cartRepository.findAll();
	}

	@Override
	public Cart create(Cart cart) {
		// TODO Auto-generated method stub
		return cartRepository.save(cart);
	}

	@Override
	public Cart update(Cart cart) {
		// TODO Auto-generated method stub
		return cartRepository.save(cart);
	}

	@Override
	public Boolean delete(Long id) {
		// TODO Auto-generated method stub
		try {
			this.cartRepository.deleteById(id);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public Cart findById(Long id) {		
		return cartRepository.findById(id).get();
	}

	@Override
	public Cart processAddCart(Long id, int quantity, User user,Cart cart) {
		cart.setProduct(productService.findById(id));
        cart.setUser(user);
        cart.setQuantity(quantity);
        cart.setTotal(quantity * productService.findById(id).getDiscount());
        cart.setStatus("Giỏ hàng");
        return cart;
	}


	@Override
	public Cart findByProductAndUserAndStatus(Product product, User user,String status) {
		// TODO Auto-generated method stub
		return  cartRepository.findByProductAndUserAndStatus(product, user, status);
	}

	@Override
	public List<Cart> findByUserAndStatus(User user, String status) {
		// TODO Auto-generated method stub
		return cartRepository.findByUserAndStatus(user, status);
	}

	@Override
	public void getCart(UserDetails userDetails, Model model) {
		User user = userService.findByUserName(userDetails.getUsername());
		List<Cart> listCarts = this.findByUserAndStatus(user, "Giỏ hàng");	
		
		if(listCarts.size() >= 2) {
			List<Cart> lastTwoCarts = listCarts.subList(listCarts.size() - 2, listCarts.size());
			model.addAttribute("lastTwoCarts", lastTwoCarts);	
		}else {
			List<Cart> lastTwoCarts = listCarts;
			model.addAttribute("lastTwoCarts", lastTwoCarts);
		}
		
	    double totalCart = 0.0;
	    for (Cart cart : listCarts) {
	    	totalCart += cart.getTotal();
	    }
	        
	    
	    model.addAttribute("totalCart", totalCart);	
	   
		model.addAttribute("listCart", listCarts);
		model.addAttribute("cartNO", listCarts.size());
		
	}

//	@Override
//	public Cart findCart(long productId, long userId) {
//		// TODO Auto-generated method stub
//		return cartRepository.findCart(productId, userId);
//	}

}
