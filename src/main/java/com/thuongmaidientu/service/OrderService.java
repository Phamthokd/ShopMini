package com.thuongmaidientu.service;

import java.util.List;

import com.thuongmaidientu.model.Cart;
import com.thuongmaidientu.model.Order;
import com.thuongmaidientu.model.User;

public interface OrderService {
	Order create(User user,List<Cart> carts, String name, String address, String phone, String note);
	List<Order> findByUser(User user);
	List<Order> findAll();
	List<Order> findOrdersByProductSupplier(String username);
	
	List<Order> findOrdersWithDetailsInProcessingStatus(Long userId, String status);
	
	Order findById(Long id);
	
	Order save(Order order);
	
	Boolean delete(Order order);
	
	Long countOrder();
	
	Long countOrder(User user);
	
	Double calculateTotalCommission();
	
	List<Object[]> calculateTotalCommissionByDay();
	
	List<Object[]> calculateTotalRevenueByDayForSeller(User user);
	
}
