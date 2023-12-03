package com.thuongmaidientu.service;

import java.util.List;

import org.springframework.data.repository.query.Param;

import com.thuongmaidientu.model.Order;
import com.thuongmaidientu.model.OrderDetails;
import com.thuongmaidientu.model.Product;
import com.thuongmaidientu.model.User;

public interface OrderDetailsService {
	List<OrderDetails> findOrderDetailsByProductSupplierAndStatus(String username, String status);

	OrderDetails findById(Long id);

	OrderDetails save(OrderDetails orderDetails);
	
	List<OrderDetails> findByOrder_user_UserNameAndStatus(String userName, String status);
	
	boolean delete(Long id);
	
}
