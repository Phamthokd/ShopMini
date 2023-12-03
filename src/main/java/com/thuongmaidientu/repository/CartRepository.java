package com.thuongmaidientu.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.thuongmaidientu.model.Cart;
import com.thuongmaidientu.model.User;

import java.util.List;
import com.thuongmaidientu.model.Product;



public interface CartRepository extends JpaRepository<Cart, Long> {
	List<Cart> findByUserAndStatus(User user, String status);
	
	Cart  findByProductAndUserAndStatus(Product product, User user, String status);
	
//	List<Cart> findByUserAndProductAndAndStatus(User user, Product product);
	
//	@Query("SELECT c FROM Cart c WHERE c.product.id = :productId AND c.user.id = :userId")
//	Cart findCart(@Param("productId") long productId, @Param("userId") long userId);
}
