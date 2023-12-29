package com.thuongmaidientu.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.thuongmaidientu.model.Order;
import com.thuongmaidientu.model.OrderDetails;
import com.thuongmaidientu.model.Product;
import com.thuongmaidientu.model.User;
import com.thuongmaidientu.repository.OrderDetailRepository;
import com.thuongmaidientu.service.OrderDetailsService;

@Service
public class OrderDetailsServiceImpl implements OrderDetailsService {

	@Autowired
	private OrderDetailRepository orderDetailRepository;
	
	@Override
	public List<OrderDetails> findOrderDetailsByProductSupplierAndStatus(String username, String status) {
		// TODO Auto-generated method stub
		return orderDetailRepository.findOrderDetailsByProductSupplierAndStatus(username, status);
	}

	@Override
	public OrderDetails findById(Long id) {
		// TODO Auto-generated method stub
		return orderDetailRepository.findById(id).get();
	}

	@Override
	public OrderDetails save(OrderDetails orderDetails) {
		// TODO Auto-generated method stub
		return orderDetailRepository.save(orderDetails);
	}

	@Override
	public List<OrderDetails> findByOrder_user_UserNameAndStatus(String userName, String status) {
		// TODO Auto-generated method stub
		return orderDetailRepository.findByOrder_user_UserNameAndStatus(userName, status);
	}

	@Override
	public boolean delete(Long id) {
		try {
			orderDetailRepository.deleteById(id);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
		
	}

	@Override
	public List<OrderDetails> findOrderDetailsByOrderIdAndUserName(Long orderId, String username) {
		
		return orderDetailRepository.findOrderDetailsByOrderIdAndUserName(orderId, username);
	}

	@Override
	public Double getTotalRevenueByUser(User user) {
		// TODO Auto-generated method stub
		 return orderDetailRepository.getTotalRevenueByUser(user);
	}

	@Override
	public List<OrderDetails> findByOrder(Order order) {
		// TODO Auto-generated method stub
		return orderDetailRepository.findByOrder(order);
	}

	@Override
	public List<OrderDetails> findOrderDetailsByuserName(String username) {
		// TODO Auto-generated method stub
		return orderDetailRepository.findOrderDetailsByuserName(username);
	}

	

}
