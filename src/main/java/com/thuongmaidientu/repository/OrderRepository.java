package com.thuongmaidientu.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.thuongmaidientu.model.Order;
import java.util.List;
import com.thuongmaidientu.model.User;


public interface OrderRepository extends JpaRepository<Order, Long> {
	List<Order> findByUserAndStatus(User user, String status);
	
	@Query("SELECT o FROM Order o " +
	           "JOIN o.orderDetails od " +
	           "JOIN od.product p " +
	           "JOIN p.userProduct u " +
	           "WHERE u.userName = :username")
	 List<Order> findOrdersByProductSupplier(@Param("username") String username);
}
