package com.thuongmaidientu.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.thuongmaidientu.model.Category;
import com.thuongmaidientu.model.Order;
import com.thuongmaidientu.model.Product;
import com.thuongmaidientu.model.User;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
	/* @Query(value = "Select p from Product p where userProduct = :userProduct") */
	List<Product> findByUserProduct(User userProduct);

	List<Product> findByCategoryAndStatus(Category category, String status);

	List<Product> findByUserProductAndStatus(User userProduct, String status);

	@Query("SELECT p FROM Product p where name LiKE %?1%")
	List<Product> searchProducts(String keyword);

	List<Product> findAllByStatusOrderByNumberOfViewsDesc(String status);

	@Query("SELECT p.name, p.numberOfOrder FROM Product p")
	List<Object[]> findOrderCountByProduct();

	
	 
	List<Product> findTop3ByUserProductOrderByNumberOfOrderDesc(User user);
	
	@Query("SELECT p, SUM(od.quantity * od.unitPrice) as revenue " +
		       "FROM Product p " +
		       "LEFT JOIN OrderDetails od ON p.id = od.product.id " +
		       "WHERE p.userProduct = :user " +
		       "GROUP BY p.id " +
		       "ORDER BY p.numberOfOrder DESC")
	List<Object[]> findTop3ProductsWithRevenueByUser(@Param("user") User user, Pageable pageable);

	
	@Query("SELECT o.createdDate, SUM(od.quantity * od.unitPrice) " +
		       "FROM Order o " +
		       "JOIN o.orderDetails od " +
		       "WHERE o.user = :user " +
		       "GROUP BY o.createdDate " +
		       "ORDER BY o.createdDate")
	List<Object[]> findRevenueAndDatesByUser(@Param("user") User user);
	
	@Query("SELECT p, SUM(od.quantity * od.unitPrice) as revenue " +
		       "FROM Product p " +
		       "LEFT JOIN OrderDetails od ON p.id = od.product.id " +
		       "WHERE p.userProduct = :user " +
		       "GROUP BY p.id " +
		       "ORDER BY p.numberOfOrder ASC")
	List<Object[]> findTop3ProductsWithRevenueByUserASC(@Param("user") User user, Pageable pageable);
	
	@Query("SELECT COUNT(p) FROM Product p WHERE p.userProduct = :user")
	Long countProduct(@Param("user") User user);
}
