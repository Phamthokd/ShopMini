package com.thuongmaidientu.service;

import java.util.List;

import com.thuongmaidientu.model.Cart;
import com.thuongmaidientu.model.Order;
import com.thuongmaidientu.model.User;

public interface OrderService {
	Order create(User user,List<Cart> carts, String name, String address, String phone, String note);
	List<Order> findByUserAndStatus(User user, String status);
	List<Order> findAll();
	List<Order> findOrdersByProductSupplier(String username);
	
	
}
