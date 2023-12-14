package com.thuongmaidientu.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.thuongmaidientu.model.Order;
import com.thuongmaidientu.model.OrderDetails;
import com.thuongmaidientu.model.Product;
import com.thuongmaidientu.model.User;


public interface OrderDetailRepository extends JpaRepository<OrderDetails, Long> {
	@Query("SELECT od FROM OrderDetails od " +
	           "JOIN od.product p " +
	           "JOIN od.order o " +
	           "JOIN p.userProduct u " +
	           "WHERE u.userName = :username " +
	           "AND od.status = :status")
	    List<OrderDetails> findOrderDetailsByProductSupplierAndStatus(
	            @Param("username") String username,
	            @Param("status") String status
	    );
	
	
	
	List<OrderDetails> findByOrder_user_UserNameAndStatus(String userName, String status);
	
	@Query("SELECT od FROM OrderDetails od " +
	           "JOIN od.product p " +
	           "JOIN od.order o " +
	           "JOIN p.userProduct u " +
	           "WHERE u.userName = :username " +
	           "AND o.id = :orderId")
    List<OrderDetails> findOrderDetailsByOrderIdAndUserName(@Param("orderId") Long orderId, @Param("username") String username);
	
	@Query("SELECT SUM(od.quantity * od.unitPrice) " +
		       "FROM OrderDetails od " +
		       "WHERE od.product.userProduct = :user")
	Double getTotalRevenueByUser(@Param("user") User user);
}
