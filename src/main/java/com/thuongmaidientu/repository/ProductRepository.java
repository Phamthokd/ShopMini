package com.thuongmaidientu.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.thuongmaidientu.model.Category;
import com.thuongmaidientu.model.Product;
import com.thuongmaidientu.model.User;
@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
	/* @Query(value = "Select p from Product p where userProduct = :userProduct") */
	List<Product> findByUserProduct(User userProduct);
	
	List<Product> findByCategory(Category category);
	
}
