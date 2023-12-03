package com.thuongmaidientu.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.thuongmaidientu.model.Cart;
import com.thuongmaidientu.model.Order;
import com.thuongmaidientu.model.OrderDetails;
import com.thuongmaidientu.model.Product;
import com.thuongmaidientu.model.User;
import com.thuongmaidientu.repository.OrderDetailRepository;
import com.thuongmaidientu.repository.OrderRepository;
import com.thuongmaidientu.service.OrderService;

@Service
public class OrderServiceImpl implements OrderService {
	
	
	@Autowired 
 	private OrderRepository orderRepository;
	
	@Autowired
	private OrderDetailRepository orderDetailRepository;

	@Override
	public Order create(User user, List<Cart> carts, String name, String address, String phone, String note) {
		Order order = new Order();
		order.setUser(user);
		order.setCustomerName(name);
		order.setCustomerAddress(address);
		order.setPhoneNumber(phone);
		order.setNote(note);
		
		double totalCart = 0.0;
	    for (Cart cart : carts) {
	    	totalCart += cart.getTotal();
	    }
	        
	    double totalValue = totalCart + 25000;
		order.setTotalAmount(totalValue);
		order.setStatus("Đã nhận đơn hàng");
//		order.setStatus("Chuẩn bị hàng");
//		order.setStatus("Đang giao");
//		order.setStatus("Hoàn thành");
		
		order = orderRepository.save(order);
		
		for(Cart cart : carts) {
			Product product = cart.getProduct();		
			OrderDetails orderDetails = new OrderDetails();		
			orderDetails.setOrder(order);
			orderDetails.setProduct(product);
			orderDetails.setQuantity(cart.getQuantity());
			orderDetails.setUnitPrice(cart.getProduct().getDiscount());	
			orderDetails.setStatus("Có đơn hàng");
			cart.setStatus("Thanh toán");
			orderDetailRepository.save(orderDetails);
			
		}
		 	
		return order;
	}

	@Override
	public List<Order> findByUserAndStatus(User user, String status) {
		// TODO Auto-generated method stub
		return orderRepository.findByUserAndStatus(user, status);
	}

	@Override
	public List<Order> findAll() {
		// TODO Auto-generated method stub
		return orderRepository.findAll();
	}

	@Override
	public List<Order> findOrdersByProductSupplier(String username) {
		// TODO Auto-generated method stub
		 return orderRepository.findOrdersByProductSupplier(username);
	}
	
}
