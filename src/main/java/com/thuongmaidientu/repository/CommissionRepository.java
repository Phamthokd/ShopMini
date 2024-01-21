package com.thuongmaidientu.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.thuongmaidientu.model.Commission;
import com.thuongmaidientu.model.User;



public interface CommissionRepository extends JpaRepository<Commission, Long> {
	@Query(value = "SELECT u, SUM(od.unitPrice * od.quantity * 0.025) AS totalRevenue,MONTH(o.createdDate) "
			+ "FROM User u " + "LEFT JOIN Product p ON u.id = p.userProduct.id "
			+ "LEFT JOIN OrderDetails od ON p.id = od.product.id " + "LEFT JOIN Order o ON od.order.id = o.id "
			+ "WHERE u.role = 'SHOP' AND MONTH(o.createdDate) = MONTH(CURRENT_DATE) " + "GROUP BY u.id, u.userName")
	List<Object[]> infoShop();
	
	Commission  findByUserAndMonth(User user, Integer month);
	
	List<Commission> findByStatusOrStatusOrStatus(String status, String status1, String status2);
	
	List<Commission> findByUserAndStatus(User user, String status);
	
	List<Commission> findByMonth(Integer month);
	
	List<Commission> findByMonthAndUser(Integer month, User user);
	

	
}
