package com.thuongmaidientu.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.thuongmaidientu.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	User findByUserName(String userName);

	List<User> findByRole(String role);

	long countByRole(String role);

	@Query(value = "SELECT u.id, u.userName, COUNT(o.id) AS orderCount, SUM(od.unitPrice * od.quantity) AS totalRevenue FROM User u LEFT JOIN Product p ON u.id = p.userProduct.id LEFT JOIN OrderDetails od ON p.id = od.product.id LEFT JOIN Order o ON od.order.id = o.id WHERE u.role = 'SHOP' GROUP BY u.id, u.userName ORDER BY orderCount DESC")
	List<Object[]> findTop5SellersByOrderCount(Pageable pageable);

	@Query(value = "SELECT u.id, u.userName, COUNT(o.id) AS orderCount, SUM(od.unitPrice * od.quantity * 0.025) AS totalRevenue, u.status "
			+ "FROM User u " + "LEFT JOIN Product p ON u.id = p.userProduct.id "
			+ "LEFT JOIN OrderDetails od ON p.id = od.product.id " + "LEFT JOIN Order o ON od.order.id = o.id "
			+ "WHERE u.role = 'SHOP' AND MONTH(o.createdDate) = MONTH(CURRENT_DATE) " + "GROUP BY u.id, u.userName")
	List<Object[]> infoShop();

}
