package com.thuongmaidientu.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.thuongmaidientu.model.Cart;
import com.thuongmaidientu.model.User;

import java.util.List;
import com.thuongmaidientu.model.Product;



public interface CartRepository extends JpaRepository<Cart, Long> {
	List<Cart> findByUserAndStatus(User user, String status);
	
	Cart findByProductAndUser(Product product, User user);
}
