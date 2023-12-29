package com.thuongmaidientu.service.impl;

import java.util.Date;
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
		
		double totalValue = 0.0;
		double commission = 0.0;
	    for (Cart cart : carts) {
	    	totalValue += cart.getTotal();
	    	
	    }
	        
	    commission = totalValue*0.025;
	    
		order.setTotalAmount(totalValue);
	    order.setCommission(commission);
	    
	    Date timestamp = new Date();
	    order.setCreatedDate(timestamp);	
		
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
			product.setNumberOfOrder(product.getNumberOfOrder()+1);
			orderDetailRepository.save(orderDetails);			
		}
		 	
		return order;
	}

	@Override
	public List<Order> findByUser(User user) {
		// TODO Auto-generated method stub
		return orderRepository.findByUser(user);
	}

	@Override
	public List<Order> findAll() {
		// TODO Auto-generated method stub
		return orderRepository.findAllOrder();
	}

	@Override
	public List<Order> findOrdersByProductSupplier(String username) {
		// TODO Auto-generated method stub
		 return orderRepository.findOrdersByProductSupplier(username);
	}

	@Override
	public List<Order> findOrdersWithDetailsInProcessingStatus(Long userId, String status) {
		// TODO Auto-generated method stub
		return orderRepository.findOrdersWithDetailsInProcessingStatus(userId, status);
	}

	@Override
	public Order findById(Long id) {
		// TODO Auto-generated method stub
		return orderRepository.findById(id).get();
	}

	@Override
	public Order save(Order order) {
		// TODO Auto-generated method stub
		return orderRepository.save(order);
	}

	@Override
	public Boolean delete(Order order) {
		
		try {
			orderRepository.delete(order);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public Long countOrder() {
		// TODO Auto-generated method stub
		return orderRepository.count();
	}

	@Override
	public Long countOrder(User user) {
		// TODO Auto-generated method stub
		return orderRepository.countOrdersByUser(user);
	}

	@Override
	public Double calculateTotalCommission() {
		
		return orderRepository.calculateTotalCommission();
	}

	@Override
	public List<Object[]> calculateTotalCommissionByDay() {
		// TODO Auto-generated method stub
		return orderRepository.calculateTotalCommissionByDay();
	}

	@Override
	public List<Object[]> calculateTotalRevenueByDayForSeller(User user) {
		// TODO Auto-generated method stub
		return orderRepository.calculateTotalRevenueByDayForSeller(user);
	}

	

	
	
}
