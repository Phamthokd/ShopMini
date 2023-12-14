package com.thuongmaidientu.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.thuongmaidientu.model.Order;
import java.util.List;
import com.thuongmaidientu.model.User;
import com.thuongmaidientu.model.OrderDetails;



public interface OrderRepository extends JpaRepository<Order, Long> {
	List<Order> findByUserAndStatus(User user, String status);
	
	@Query("SELECT o FROM Order o " +
	           "JOIN o.orderDetails od " +
	           "JOIN od.product p " +
	           "JOIN p.userProduct u " +
	           "WHERE u.userName = :username")
	 List<Order> findOrdersByProductSupplier(@Param("username") String username);
	
	 @Query("SELECT o FROM Order o "
	 		+ "JOIN o.orderDetails od "
	 		+ "JOIN od.product p "
	 		+ "WHERE od.status = :status "
	 		+ "AND p.userProduct.id = :userId")
	List<Order> findOrdersWithDetailsInProcessingStatus(@Param("userId") Long userId,@Param("status") String status);

	List<Order> findByUser(User user);
	
	@Query("SELECT COUNT(o) FROM Order o WHERE o.id IN (SELECT od.order.id FROM OrderDetails od WHERE od.product.userProduct = :user)")
	Long countOrdersByUser(@Param("user") User user);
	 
	@Query(value = "SELECT SUM(o.commission) FROM Order o")
	Double calculateTotalCommission();
	
	@Query(value = "SELECT DATE(o.createdDate) AS orderDate, SUM(o.commission) AS totalCommission FROM Order o GROUP BY DATE(o.createdDate)")
	List<Object[]> calculateTotalCommissionByDay();
	
	@Query(value = "SELECT DATE(o.createdDate) AS orderDate, SUM(od.unitPrice * od.quantity) AS totalRevenue " +
            "FROM Order o " +
            "JOIN o.orderDetails od " +
            "WHERE od.product.userProduct = :user " +
            "GROUP BY DATE(o.createdDate)")
	List<Object[]> calculateTotalRevenueByDayForSeller(@Param("user") User user);
}
